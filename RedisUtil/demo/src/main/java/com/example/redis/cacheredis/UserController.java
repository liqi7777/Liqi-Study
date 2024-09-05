package com.example.redis.cacheredis;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Sky
 * create 2020/01/08
 * email sky.li@ixiaoshuidi.com
 **/
@RequestMapping("cacheredis")
@RestController
public class UserController {
    @Resource
    private UserDao userDao;

    /**
     * 查询出一条数据并且添加到缓存
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUser")                  //key,keyGenerator两者只能有一个存在，key属性：#参数名”或者“#p参数index
    @Cacheable(value = "userCache", keyGenerator = "mykeyGenerator")
    public User getPrud(@RequestParam(required = true) String userId) {
        System.out.println("如果没有缓存，就会调用下面方法，如果有缓存，则直接输出，不会输出此段话");
        return userDao.getUser(Integer.parseInt(userId));
    }

    /**
     * 删除一个缓存
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    @CacheEvict("userCache")
    public String deleteUser(@RequestParam(required = true) String userId) {
        return "删除成功";
    }


    /**
     * 添加一条保存的数据到缓存，缓存的key是当前user的id
     *
     * @param user
     * @return
     */
    @PostMapping("/saveUserInner")
    @Cacheable(value = "userCache", key = "#user.testToString() +''")
    public User saveUserInner(User user) {
        return saveUser(user);
    }



    /**
     * 添加一条保存的数据到缓存，缓存的key是当前user的id
     *
     * @param user
     * @return
     */
    @PostMapping("/saveUser")
    @Cacheable(value = "userCache", key = "#root.methodName+#user.testToString() +''")
    public User saveUser(User user) {
        return user;
    }


    /**
     * 返回结果userPassword中含有nocache字符串就不缓存
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUser2", method = RequestMethod.GET)
    @CachePut(value = "userCache", unless = "#result.userPassword.contains('nocache')")
    public User getUser2(@RequestParam(required = true) String userId) {
        System.out.println("如果走到这里说明，说明缓存没有生效！");
        User user = new User(Integer.parseInt(userId), "name_nocache" + userId, "nocache");
        return user;
    }


    @RequestMapping(value = "/getUser3", method = RequestMethod.GET)
    @Cacheable(value = "userCache", key = "#root.targetClass.getName() + #root.methodName + #userId")
    public User getUser3(@RequestParam(required = true) String userId) {
        System.out.println("如果第二次没有走到这里说明缓存被添加了");
        return userDao.getUser(Integer.parseInt(userId));
    }
}
