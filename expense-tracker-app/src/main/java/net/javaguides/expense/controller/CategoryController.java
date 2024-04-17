package net.javaguides.expense.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.javaguides.expense.dto.CategoryDto;
import net.javaguides.expense.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "CRUD REST APIs for Category Resource",
        description = "CRUD REST APIs for Category Resource - Create Category"+
                "Update Category, Get Category, and Delete Category"
)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
     private CategoryService categoryService;
     public CategoryController(CategoryService categoryService){
         this.categoryService = categoryService;
     }

     @Operation(
             summary = "Create Category Rest Api",
             description = "Create Category Rest Api to save category infor into database"
     )
     @ApiResponse(
             responseCode = "201",
             description = "HTTP STATUS 201 CREATED"
     )
     @PostMapping
     public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
         return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
     }

    @Operation(
            summary = "Get Category Rest Api",
            description = "Get Category Rest Api to get category infor from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
     @GetMapping("{id}")
     public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
         return ResponseEntity.ok(categoryService.getCategoryById(id));
     }

    @Operation(
            summary = "Get All Category Rest Api",
            description = "Get All Category Rest Api to get category infor from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
     @GetMapping
     public ResponseEntity<List<CategoryDto>> getAllCategories(){
         return ResponseEntity.ok(categoryService.getAllCategories());
     }

    @Operation(
            summary = "Update Category Rest Api",
            description = "Update Category Rest Api to update category infor from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
     @PutMapping("{id}/update")
     public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long categoryId,
                                                       @RequestBody CategoryDto categoryDto){
         return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
     }

    @Operation(
            summary = "Delete Category Rest Api",
            description = "Delete Category Rest Api to delete category infor from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
     @DeleteMapping("{id}/delete")
     public ResponseEntity<String> deleteCategory(@PathVariable Long id){
         categoryService.deleteCategory(id);
         return ResponseEntity.ok("the category is successfully deleted");
     }
}
