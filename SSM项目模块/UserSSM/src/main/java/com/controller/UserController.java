package com.controller;


import com.domain.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result save(@RequestBody User user) {
        boolean flag = userService.save(user);
        return new Result(flag?Code.SAVE_ok:Code.SAVE_err,flag);
    }

    @PutMapping
    public Result update(@RequestBody User user) {
        System.out.println(user);

        boolean flag =  userService.update(user);
        return new Result(flag?Code.UPDATE_ok:Code.UPDATE_err,flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        boolean flag =  userService.delete(id);
        return new Result(flag?Code.DELETE_ok:Code.DELETE_err,flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        System.out.println(1);
        User user = userService.getById(id);
        Integer code = user !=null ? Code.GET_ok:Code.GET_err;
        String msg = user != null ? "":"数据查询失败";

        return new Result(code,user,msg);
    }

    @GetMapping
    public Result getAll() {
        List<User> users = userService.getAll();
        Integer code = users !=null ? Code.GET_ok:Code.GET_err;
        String msg = users != null ? "":"数据查询失败";

        return new Result(code,users,msg);
    }

}
