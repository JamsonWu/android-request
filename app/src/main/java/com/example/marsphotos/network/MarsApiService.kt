package com.example.marsphotos.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET


// 后台接口根服务地址：
private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"


// 创建Retrofit实例对象
private val retrofit = Retrofit.Builder()
    // 添加转换器，比如转换为JSON格式等类型
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    // 加入服务基础URL
    .baseUrl(BASE_URL)
    // 构建一个对象
    .build()


// 后台服务接口定义
interface MarsApiService {
    // 获取图片方法声明
    // 注解 @GET告知这是GET请求
    // photos代表终结点即后台服务接口对外公布的方法名
    // 添加 suspend 声明为异步方法
    @GET("photos")
    suspend fun getPhotos():List<MarsPhoto>
}


// 给后台接口调用创建代理实例
object MarsApi {
    // 创建接口MarsApiService的代理对象，使用Proxy.newProxyInstance创建代理
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
