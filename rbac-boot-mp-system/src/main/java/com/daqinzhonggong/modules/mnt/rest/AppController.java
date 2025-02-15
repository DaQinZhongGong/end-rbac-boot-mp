package com.daqinzhonggong.modules.mnt.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daqinzhonggong.annotation.Log;
import com.daqinzhonggong.modules.mnt.domain.App;
import com.daqinzhonggong.modules.mnt.domain.vo.AppQueryCriteria;
import com.daqinzhonggong.modules.mnt.service.AppService;
import com.daqinzhonggong.utils.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Api(tags = "运维：应用管理")
@RequestMapping("/api/app")
public class AppController {

    private final AppService appService;

    @ApiOperation("导出应用数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('app:list')")
    public void exportApp(HttpServletResponse response, AppQueryCriteria criteria) throws IOException {
        appService.download(appService.queryAll(criteria), response);
    }

    @ApiOperation(value = "查询应用")
    @GetMapping
    @PreAuthorize("@el.check('app:list')")
    public ResponseEntity<PageResult<App>> queryApp(AppQueryCriteria criteria, Page<Object> page) {
        return new ResponseEntity<>(appService.queryAll(criteria, page), HttpStatus.OK);
    }

    @Log("新增应用")
    @ApiOperation(value = "新增应用")
    @PostMapping
    @PreAuthorize("@el.check('app:add')")
    public ResponseEntity<Object> createApp(@Validated @RequestBody App resources) {
        appService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改应用")
    @ApiOperation(value = "修改应用")
    @PutMapping
    @PreAuthorize("@el.check('app:edit')")
    public ResponseEntity<Object> updateApp(@Validated @RequestBody App resources) {
        appService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除应用")
    @ApiOperation(value = "删除应用")
    @DeleteMapping
    @PreAuthorize("@el.check('app:del')")
    public ResponseEntity<Object> deleteApp(@RequestBody Set<Long> ids) {
        appService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
