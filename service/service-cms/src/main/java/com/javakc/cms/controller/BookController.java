package com.javakc.cms.controller;

import com.javakc.cms.entity.Book;
import com.javakc.cms.service.BookService;
import com.javakc.cms.vo.BookQuery;
import com.javakc.commonutils.api.APICODE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "书籍管理")
@RequestMapping("/cms/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "查询所有书籍")
    @GetMapping
    public APICODE findAll(){
//        int i=1/0;
        List<Book> list = bookService.findAll();
        return APICODE.OK().data("items",list);
    }

    @PostMapping("{pageNo}/{pageSize}")
    @ApiOperation(value = "根据条件进行分页查询")
    public APICODE findPageBook(@RequestBody(required = false)BookQuery bookQuery,
                                @PathVariable(name = "pageNo") int pageNo,
                                @PathVariable(name = "pageSize") int pageSize){

        //调用service的方法进行带条件的分页查询
        Page<Book> page = bookService.findPageBook(bookQuery, pageNo, pageSize);
        //总条数
        long totalElements = page.getTotalElements();
        //当前页查询到的数据
        List<Book> list = page.getContent();
        return APICODE.OK().data("total",totalElements).data("items",list);
    }


    @PostMapping("saveBook")
    @ApiOperation(value = "添加书籍")
    public APICODE saveBook(@RequestBody Book book){

        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }


    @ApiOperation(value = "根据ID查询")
    @GetMapping("{bookId}")
    public APICODE getByBookId(@PathVariable(name = "bookId") String bookId){

        Book book = bookService.getById(bookId);
        return APICODE.OK().data("book",book);
    }


    @ApiOperation(value = "根据Id修改书籍")
    @PutMapping("updateBook")
    public APICODE updateBook(@RequestBody Book book){

        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

    @DeleteMapping("{bookId}")
    @ApiOperation(value = "根据Id删除")
    public APICODE deleteByBookId(@PathVariable(name = "bookId") String bookId){
        bookService.removeById(bookId);
        return APICODE.OK();
    }
}
