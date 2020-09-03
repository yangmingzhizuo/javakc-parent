package com.javakc.cms.service;

import com.javakc.cms.dao.BookDao;
import com.javakc.cms.entity.Book;
import com.javakc.cms.vo.BookQuery;
import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BookService extends BaseService<BookDao,Book> {  //为什么是BookDao

    @Autowired
    private BookDao bookDao;

    public List<Book> findAll(){

        return bookDao.findAll();
    }

    /**
     * 带条件的分页查询-书籍管理
     * @param bookQuery 封装的查询条件
     * @param pageNo 当前页
     * @param pageSize 最大页
     * @return
     */
    public Page<Book> findPageBook(BookQuery bookQuery,int pageNo,int pageSize){

        //设置查询条件
        SimpleSpecificationBuilder simpleSpecificationBuilder = new SimpleSpecificationBuilder<>();
        if (bookQuery!=null){
            if (!StringUtils.isEmpty(bookQuery.getTitle())){
                //模糊查询  :是模糊查询
                simpleSpecificationBuilder.and("title",":",bookQuery.getTitle());
            }
        }

        Specification<Book> specification = simpleSpecificationBuilder.getSpecification();

        //查询
        Page<Book> page = dao.findAll(specification, PageRequest.of(pageNo - 1, pageSize));
        return page;

    }
}
