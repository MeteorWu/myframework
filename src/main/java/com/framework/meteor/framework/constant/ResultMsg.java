package com.framework.meteor.framework.constant;

public enum ResultMsg {

    SUCCESS("SUCCESS", "操作成功完成"),
    /********************************************** 模块通用 **********************************************************/
    FAULT_ACCESS_TOKEN("10001", "您没有权限访问该接口"),
    NO_ACCESS_TOKEN("10002", "缺少访问授权参数"),
    PARAMETER_EMPTY("10003", "参数不能为空"),
    PARAMETER_INCORRECT("10004", "参数错误"),
    USER_VERIFICATION_CODE_INCORRECT("10005", "验证码错误"),
    PHONE_ERROR("10006", "手机参数错误"),
    USERDETAILS_NOT_EXIST("10007", "该用户没有认证权限"),
    AUTH_FAIL("10008", "认证失败"),
    PARAMETER_NOT_ENOUGH("10009", "必填项请求参数不完整，请检查"),
    PAGE_FLIP_PARAMETER_ERROR("10010", "请输入正确的分页参数"),
    NOT_DELETE_ADMIN("10011","老板不能删除"),
    START_TIME_IS_NULL("10012", "请设置查询条件：开始时间"),
    ORDER_HAVE_FINISH("100104", "订单已经完成不能删除"),
    SIGN_ERROR("10105", "签名错误"),
    VERSION_ERROR("99999", "软件版本太低，请升级软件"),

    /********************************************** 客户模块 **********************************************************/
    CLIENT_PRODUCT_EXIST("11000", "商品中存在客户信息，不能删除"),
    CLIENT_HAVE_TRADE_RECORD("11001", "客户存在交易记录，不能删除"),
    CLIENTID_IS_NULL("11002", "clientId为空，请检查"),

    /********************************************** 用户模块 **********************************************************/

    USERNAME_PASSWORD_EMTYY("20001", "用户名或密码不能为空"),
    USER_NOT_EXIST("20002", "该用户不存在"),
    USERNAME_PASSWORD_ERROR("20003", "用户名或密码错误"),
    SHOP_CODE_NOT_EXIST("20004","商铺或邀请码不存在"),
    USERID_NOT_NULL("20005","用户ID不能为空或用户ID错误"),
    CLIENT_NOT_EXIST("20006","客户不存在"),
    USER_ROLE_ERROR("20007", "无权操作"),

    /********************************************** 店铺模块 **********************************************************/
    SHOP_HAVE_ACTIVATED("30001", "店铺已经激活"),
    SHOP_NAME_EXIST("30002", "店铺名字已经存在"),
    SHOP_USERID_NOTNULL("30003","用户ID不能为空"),
    SHOPID_IS_NULL("30004", "shopId为空，请检查"),
    SHOPID_IS_MISSING("30005", "缺少参数：shopId，请检查"),
    SHOPID_INFO_NULL("30006", "查询店铺信息失败，请检查"),
    SHOPID_STOP("30007", "店铺禁用，请联系管理员"),
    ERROR_CODE("30008", "错误的邀请码"),
    SHOP_NOT_EXIST("30009", "店铺已存在"),

    /********************************************** 商品模块 **********************************************************/

    PRODUCT_NUMBER_EXIST("40001", "商品款号已经存在"),
    PRODUCT_STORE_NOT_EMPTY("40002", "商品库存不为0"),
    PRODUCT_NOT_EXIST("40003", "商品不存在"),
    PRODUCT_SALE_SUSPEND("40004", "商品存在销售挂单，请处理"),
    PRODUCT_NAME_EXIST("40005", "商品名字已经存在"),

    CORLOR_EXIST("40006", "此商品颜色已经存在"),
    SIZE_EXIST("40007", "此商品尺码已经存在"),
    UNIT_EXIST("40008", "此商品单位已经存在"),
    TYPE_EXIST("40009", "此商品尺码类型已经存在"),
    STYLE_EXIST("40010", "此商品款式已经存在"),
    MATRAIL_EXIST("40011", "此商品面料已经存在"),
    PRODUCT_HAVE_STORE_IN("40012", "入库商品，商品不允许删除或者修改部分属性"),
    PRODUCT_STORE_IN_SUSPEND("40013", "商品有入库挂单，不能删除"),

    /********************************************** 库存模块 **********************************************************/
    STORE_EMPTY("50001", "库存为空"),
    CURRENT_PRODUCT_STORE_NOT_ENOUGH("50002", "当前商品库存不足"),
    STORE_DETAIL_MISS("50003", "库存明细表数据缺失"),
    NOT_FIND_STOREIN("50004", "未找到该挂单记录"),
    STORE_IN_RECORD_FINISH("50005", "盘点订单已经完成，不能修改"),
    PRODUCT_IN_CHECK("50006", "商品正在盘点，不能入库"),

    /********************************************** 盘点模块 **********************************************************/
    CHECK_RECORD_FINISH("51001", "盘点订单已经完成，不能修改"),
    CHECK_MUST_BE_STORED("51002", "未入库商品不能盘点"),
    PRODUCT_IN_SALE("51003", "盘点商品正在销售挂单，请先处理销售挂单"),



    /********************************************** 订单模块 **********************************************************/
    ORDER_BASE_NOT_ENOUGH("60001", "订单基本信息参数不完整，请检查"),
    ORDER_PRODUCT_NOT_ENOUGH("60002", "订单商品信息集合参数不完整，请检查"),
    ORDER_PRODUCT_DETAIL_NOT_ENOUGH("60003", "订单productId、unitPrice、detail等参数不完整，请检查"),
    ORDER_DETAIL_NOT_ENOUGH("60004", "订单color、sizedata等参数不完整，请检查"),
    ORDER_DETAIL_SIZEDATA_NOT_ENOUGH("60005", "订单size、amount等属性参数不完整，请检查"),
    ORDER_STATUS_ERROR("60006", "订单状态有误，请检查"),
    ORDERID_IS_NULL("60007", "缺少参数：orderId，请检查"),
    ORDER_NOT_EXIST("60008", "订单不存在"),
    ORDER_TYPE_STATUS_ERROR("60009", "订单类型或状态有误，请检查"),
    ORDER_DETAIL_MISSING("60010", "订单明细信息缺失，请检查"),
    ORDER_CANNOT_DELETE("60011", "订单状态错误不能删除，请检查"),
    SYSTEM_AMOUNT_LACK("60012", "库存数量不足"),
    ORDER_STORE_ERROR("60013", "库存缺失订单中商品的，请检查"),
    PRODUCT_IN_CHECK_SALE("60014", "商品正在盘点，不能销售"),

    /********************************************** 员工模块 **********************************************************/
    EMPLOYEE_TRADE_RECORD("70001", "员工已经存在挂单记录，不能删除"),
    BOSS_CANNOT_DELETE("70002", "老板不能被删除"),




    SYSTEM_ERROR("30000", "系统异常"),

    INTERNAL_SERVER_ERROR("500", "请求的服务产生异常"),

    NULL_POINT_ERROR("2001", "空指针异常"),

    SQL_ERROR("2002", "SQL执行异常"),

    OTHER_ERROR("9001", "其他异常"),



    NO_TOKEN("10101", "缺少参数[tokenId]"),

    FAULT_TOKEN("10102", "错误的Token"),

    EXPIRE_TOKEN("10103", "Token已过期，请重新登录"),

    USER_ALREADY_EXIST("10201", "该手机号已注册"),

    USER_PASSWORD_INCORRECT("10206", "密码错误"),

    USER_FAULT_PHONE("10211", "错误的手机号"),

    PERMISSION_FORBIDDEN("10300", "无权操作");


    private String code;
    private String msg;
    private ResultMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
