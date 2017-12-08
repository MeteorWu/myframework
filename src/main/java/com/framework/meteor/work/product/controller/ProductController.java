package com.framework.meteor.work.product.controller;

import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.model.Response;
import com.framework.meteor.framework.model.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * 产品模块管理
 *
 * @author Meteor.wu
 * @since 2017/11/29 16:33
 */
@Api(value = "product", description = "产品模块")
@RestController
@RequestMapping("/product")
public class ProductController {

//    @Autowired
//    private ProductDao productDao;

    @PostMapping(value = "/addProduct")
    @ApiOperation(value = "生成product测试")
    public Response addProduct(
            @ApiParam(required = true, name = "userId", value = "操作者ID") @RequestParam Long userId,
            @ApiParam(required = true, name = "token", value = "token") @RequestParam String token,
            @ApiParam(required = true, name = "sign", value = "令牌") @RequestParam String sign,
            @ApiParam(required = true) @RequestBody JSONObject json
    ) throws Exception {

//        Product product = new Product();
//        product.setName("chanping");
//        product.setProductId(UUIDUtil.getUUID());
//        product.setEnabled(BooleanEnum.TRUE.ordinal());
//
//        User user = new User();
//        user.setUserId(UUIDUtil.getUUID());
//        user.setSex(Sex.FEMALE.ordinal());
//        user.setUsername("username");
//        user.setPassword("123456");
//        product.setCreateUser(user);
//
//        Set<Order> set = new HashSet();
//        Order order = new Order();
//        order.setOrderId(UUIDUtil.getUUID());
//        order.setProduct(product);
//        set.add(order);
//        product.setOrderSet(set);
//        productDao.save(product);

        JSONObject ret = new JSONObject();
        ret.put("success", "successful");
        return new ResponseBody<>(ResultMsg.SUCCESS,ret);
    }

    @PostMapping(value = "/deleteProduct")
    @ApiOperation(value = "生成product测试")
    public Response deleteProduct(
            @ApiParam(required = true, name = "userId", value = "操作者ID") @RequestParam Long userId,
            @ApiParam(required = true, name = "token", value = "token") @RequestParam String token,
            @ApiParam(required = true, name = "sign", value = "令牌") @RequestParam String sign,
            @ApiParam(required = true) @RequestBody JSONObject json
    ) throws Exception {


        return new ResponseBody<>(ResultMsg.SUCCESS);
    }

    @PostMapping(value = "/getByNames")
    @ApiOperation(value = "根据多名字查询")
    public Response getByNames(
            @ApiParam(required = true, name = "userId", value = "操作者ID") @RequestParam Long userId,
            @ApiParam(required = true, name = "token", value = "token") @RequestParam String token,
            @ApiParam(required = true, name = "sign", value = "令牌") @RequestParam String sign,
            @ApiParam(required = true) @RequestBody JSONObject json
    ) throws Exception {
//        List<String> names = new ArrayList<>();
//        names.add("111");
//        names.add("bbbb");
//        List<Product> list = productDao.getByNameInAndEnabledGreaterThan(names, -1);
        return new ResponseBody<>(ResultMsg.SUCCESS);
    }

    @PostMapping(value = "/getById")
    @ApiOperation(value = "根据多名字查询")
    public Response getById(
            @ApiParam(required = true, name = "userId", value = "操作者ID") @RequestParam Long userId,
            @ApiParam(required = true, name = "token", value = "token") @RequestParam String token,
            @ApiParam(required = true, name = "sign", value = "令牌") @RequestParam String sign,
            @ApiParam(required = true) @RequestBody JSONObject json
    ) throws Exception {
//        Product product = productDao.getByProductId("d196d66fc8824fb9b0028941ff43a930");
        return new ResponseBody<>(ResultMsg.SUCCESS);
    }
}
