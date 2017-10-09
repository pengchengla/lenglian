package com.example.administrator.lenglian.pay;

public class PayKeys {
    //
    // 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，
    // 并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。
    // 这里签名时，只需要使用生成的RSA私钥。
    // Note: 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。

    //合作身份者id，以2088开头的16位纯数字 此id用来支付时快速登录
    public static final String DEFAULT_PARTNER = "";
    //收款支付宝账号
    public static final String DEFAULT_SELLER = "";
    /*
    商户私钥，自助生成，在压缩包中有openssl，用此软件生成商户的公钥和私钥，
    写到此处要不然服务器返回错误。公钥要传到淘宝合作账户里，详情请看淘宝的sdk文档
     */
}

