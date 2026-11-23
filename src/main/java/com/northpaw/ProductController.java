package com.northpaw;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final BrandRepository brandRepo;
    private final CategoryRepository categoryRepo;
    private final PetTypeRepository petTypeRepo;
    private final ItemRepository itemRepo;
    private final ItemVariantRepository variantRepo;
    private final DistributionCentreRepository dcRepo;
    private final InventoryRepository inventoryRepo;

    public ProductController(BrandRepository brandRepo, CategoryRepository categoryRepo,
                             PetTypeRepository petTypeRepo, ItemRepository itemRepo,
                             ItemVariantRepository variantRepo, DistributionCentreRepository dcRepo,
                             InventoryRepository inventoryRepo) {
        this.brandRepo = brandRepo;
        this.categoryRepo = categoryRepo;
        this.petTypeRepo = petTypeRepo;
        this.itemRepo = itemRepo;
        this.variantRepo = variantRepo;
        this.dcRepo = dcRepo;
        this.inventoryRepo = inventoryRepo;
    }

    // ---------- informational pages ---------

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("petTypes", petTypeRepo.findAllByOrderByNameAsc());
        model.addAttribute("productCount", variantRepo.count());
        model.addAttribute("brandCount", brandRepo.count());
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("productCount", variantRepo.count());
        model.addAttribute("brandCount", brandRepo.count());
        model.addAttribute("centreCount", dcRepo.count());
        return "about";
    }

    @GetMapping("/centres")
    public String centres(Model model) {
        model.addAttribute("centres", dcRepo.findAll(Sort.by("province", "city")));
        return "centres";
    }

    // ---------- list with filter, sort and paging ----------

    @GetMapping("/products")
    public String list(@RequestParam(required = false) String q,
                       @RequestParam(required = false) Long brandId,
                       @RequestParam(required = false) Long petTypeId,
                       @RequestParam(defaultValue = "newest") String sort,
                       @RequestParam(defaultValue = "desc") String dir,
                       @RequestParam(defaultValue = "0") int page,
                       Model model) {

        
        String sortField = "createdAt";
        if (sort.equals("name")) sortField = "item.name";
        if (sort.equals("price")) sortField = "unitPrice";
        Sort.Direction direction = dir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, 5, Sort.by(direction, sortField).and(Sort.by("id")));
        // search text: ignore blanks, otherwise match anywhere in name, brand or SKU
        String text = (q == null || q.isBlank()) ? null : q.trim();
        String pattern = (text == null) ? null : "%" + text.toLowerCase() + "%";
        Page<ItemVariant> variants = variantRepo.search(pattern, brandId, petTypeId, pageable);

        model.addAttribute("variants", variants);
        model.addAttribute("brands", brandRepo.findAllByOrderByNameAsc());
        model.addAttribute("petTypes", petTypeRepo.findAllByOrderByNameAsc());
        model.addAttribute("q", text);
        model.addAttribute("brandId", brandId);
        model.addAttribute("petTypeId", petTypeId);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        return "list";
    }

    // ---------- add product form ----------

    @GetMapping("/products/new")
    public String showForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        addDropdownLists(model);
        return "form";
    }

    @PostMapping("/products")
    @Transactional
    public String saveProduct(@Valid @ModelAttribute("productForm") ProductForm form,
                              BindingResult result, Model model, RedirectAttributes redirect) {

        Brand brand = form.getBrandId() == null ? null : brandRepo.findById(form.getBrandId()).orElse(null);
        if (form.getBrandId() != null && brand == null)
            result.rejectValue("brandId", "invalid", "Please choose a brand from the list.");

        Category category = form.getCategoryId() == null ? null : categoryRepo.findById(form.getCategoryId()).orElse(null);
        if (form.getCategoryId() != null && category == null)
            result.rejectValue("categoryId", "invalid", "Please choose a category from the list.");

        PetType petType = form.getPetTypeId() == null ? null : petTypeRepo.findById(form.getPetTypeId()).orElse(null);
        if (form.getPetTypeId() != null && petType == null)
            result.rejectValue("petTypeId", "invalid", "Please choose a pet type from the list.");

        
        List<DistributionCentre> carriers = new ArrayList<>();
        if (form.getDcIds() != null) {
            for (Long dcId : new LinkedHashSet<>(form.getDcIds())) {
                DistributionCentre dc = dcRepo.findById(dcId).orElse(null);
                if (dc == null) {
                    result.rejectValue("dcIds", "invalid", "Please choose distribution centres from the list.");
                    break;
                }
                carriers.add(dc);
            }
        }

        if (form.getSku() != null && variantRepo.existsBySku(form.getSku()))
            result.rejectValue("sku", "duplicate", "That SKU is already in use.");
        if (form.getBarcode() != null && variantRepo.existsByBarcode(form.getBarcode()))
            result.rejectValue("barcode", "duplicate", "That barcode is already in use.");

        if (result.hasErrors()) {
            addDropdownLists(model);
            return "form";
        }

        Item item = new Item();
        item.setName(form.getName().trim());
        item.setDescription(form.getDescription());
        item.setBrand(brand);
        item.setCategory(category);
        item.setPetType(petType);
        itemRepo.save(item);

        ItemVariant variant = new ItemVariant();
        variant.setItem(item);
        variant.setSku(form.getSku());
        variant.setSize(form.getSize().trim());
        variant.setFlavour(form.getFlavour() == null || form.getFlavour().isBlank() ? null : form.getFlavour().trim());
        variant.setBarcode(form.getBarcode());
        variant.setUnitPrice(form.getUnitPrice());
        variantRepo.save(variant);

        for (DistributionCentre dc : carriers) {
            Inventory inventory = new Inventory();
            inventory.setVariant(variant);
            inventory.setDc(dc);
            inventory.setQuantityOnHand(0);   
            inventoryRepo.save(inventory);
        }

        redirect.addFlashAttribute("saved", true);
        return "redirect:/products/" + variant.getId();
    }

    // ---------- detail ----------

    @GetMapping("/products/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ItemVariant variant = variantRepo.findById(id).orElse(null);
        if (variant == null) {
            return "redirect:/products";
        }
        model.addAttribute("variant", variant);
        model.addAttribute("stock", inventoryRepo.findByVariantId(id));
        return "detail";
    }

    private void addDropdownLists(Model model) {
        model.addAttribute("brands", brandRepo.findAllByOrderByNameAsc());
        model.addAttribute("categories", categoryRepo.findAllByOrderByNameAsc());
        model.addAttribute("petTypes", petTypeRepo.findAllByOrderByNameAsc());
        model.addAttribute("centres", dcRepo.findAll(Sort.by("name")));
    }
}
