package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // 获取所有用户
    @GetMapping
    public Result<String> getUsers() {
        if (users.isEmpty()) {
            return Result.success("暂无用户数据");
        }
        StringBuilder result = new StringBuilder("用户列表：\n");
        for (User user : users) {
            result.append("ID: ").append(user.getId())
                    .append(", 姓名: ").append(user.getName())
                    .append(", 年龄: ").append(user.getAge()).append("\n");
        }
        return Result.success(result.toString());
    }

    // 根据ID获取用户
    @GetMapping("/{id}")
    public Result<String> getUserById(@PathVariable Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                String info = "用户信息：ID: " + user.getId()
                        + ", 姓名: " + user.getName()
                        + ", 年龄: " + user.getAge();
                return Result.success(info);
            }
        }
        return Result.success("用户不存在");
    }

    // 创建用户
    @PostMapping
    public Result<String> createUser(@RequestBody User user) {
        user.setId(idGenerator.getAndIncrement());
        users.add(user);
        return Result.success("用户创建成功，ID: " + user.getId());
    }

    // 更新用户
    @PutMapping("/{id}")
    public Result<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                user.setId(id);
                users.set(i, user);
                return Result.success("用户更新成功");
            }
        }
        return Result.success("用户不存在");
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable Long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return Result.success("用户删除成功");
            }
        }
        return Result.success("用户不存在");
    }
}