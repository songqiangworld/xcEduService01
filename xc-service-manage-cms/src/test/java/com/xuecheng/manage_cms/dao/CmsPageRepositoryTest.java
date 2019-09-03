package com.xuecheng.manage_cms.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:11
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    /*
     * 功能描述: <br>
     * 〈〉
     * @Param: []
     * @Return: void
     * @Author: songqiang
     * @Date: 2019/9/3 16:45
     */
    @Test
    public void testGridFsTemplate() throws FileNotFoundException {
        ObjectId id = gridFsTemplate.store(new FileInputStream(new File("E:\\test\\1.txt")), "test.txt");
        System.out.println(id);
    }

    /*
     * 功能描述: <br>
     * 〈〉
     * @Param: []
     * @Return: void
     * @Author: songqiang
     * @Date: 2019/9/3 16:45
     */
    @Test
    public void testGridFsTemplate2() throws Exception {
        //根据id查询文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5d6e2e73686c93381074fad1")));
        //获取下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //获取下载流
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
        //从流中读取数据
        String s = IOUtils.toString(gridFsResource.getInputStream(), "gbk");

        System.out.println("111"+s);
    }

    @Test
    public void testFindAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);

    }

    //分页查询
    @Test
    public void testFindPage(){
        //分页参数
        int page = 1;//从0开始
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);
    }

    //修改
    @Test
    public void testUpdate() {
        //查询对象
        Optional<CmsPage> optional = cmsPageRepository.findById("5b4b1d8bf73c6623b03f8cec");
        if(optional.isPresent()){
            CmsPage cmsPage = optional.get();
            //设置要修改值
            cmsPage.setPageAliase("test01");
            //...
            //修改
            CmsPage save = cmsPageRepository.save(cmsPage);
            System.out.println(save);
        }

    }

    //根据页面名称查询
    @Test
    public void testfindByPageName(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("测试页面");
        System.out.println(cmsPage);
    }
}
