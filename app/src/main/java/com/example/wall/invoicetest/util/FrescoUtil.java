package com.example.wall.invoicetest.util;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Fresco处理工具类
 */
public class FrescoUtil {

    /**
     * 初始化方法
     * @param context
     */
    public static void initFresco(Context context){
        Fresco.initialize(context);
    }
    public static void initFresco(Context context, ImagePipelineConfig imagePipelineConfig){
        Fresco.initialize(context,imagePipelineConfig);
    }

    /**
     * 图片的普通加载
     * @param url
     * @param simpleDraweeView
     */
    public static void imageViewBind(String url, SimpleDraweeView simpleDraweeView){
        Uri uri = Uri.parse(url);
        simpleDraweeView.setImageURI(uri);
    }

    /**
     * 开启图片加载重试
     * @param url
     * @param simpleDraweeView
     */
    public static void imageViewBindRetry(String url, SimpleDraweeView simpleDraweeView){
        Uri uri = Uri.parse(url);
        AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(build);
    }

}
