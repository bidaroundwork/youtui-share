youtui-template
友推SDK是一款是面向移动应用的SDK组件,提供给开发者集成使用。通过友推,开发者几行代码就可以为应用添加分享送积分功能,并提供详尽的统计报表.youtui-template提供两套模板，开发者可以
轻松的集成分享到qq,微信，新浪微博，腾讯微博，人人等主流平台的功能.如果开发者希望自定界面，则可以使用youtui-core来集成.

集成方法如下：

3.1 申请应用 AppKey

如果您已经在友推添加过App并已生成AppKey，可跳过本步骤直接进入3.2继续。

申请方法：访问友推网站后台，登录后进入应用列表，添加需要集成友推组件的 App，如下图，添加成功后可获取应用的 AppKey。


![](http://youtui.mobi/doc/media/add_app.png)
图：在友推网站添加登记应用信息

![](http://youtui.mobi/doc/media/get_appkey.png)
图：在友推网站添加登记应用信息

3.2 申请社交平台appkey

集成前您需要为您的应用在各大社交网站的开放平台申请账号并通过审核，否则只能调用系统的分享菜单，无法跟踪分享的回调事件及统计

平台	网址
微信	http://open.weixin.qq.com
新浪微博	http://open.weibo.com
腾讯微博	http://dev.t.qq.com
QQ空间、QQ	http://open.qq.com/
微信好友	http://open.weixin.qq.com
人人	http://dev.renren.com
豆瓣(暂未支持)	http://developers.douban.com/
FaceBook(暂未支持)	https://developers.facebook.com
Twitter(暂未支持)	https://dev.twitter.com
 
 4. 集成说明
SDK 集成使用流程：

1.引用友推库项目-> 2.注册需要分享的平台 -> 3.AndroidManifest.xml注册权限、activity ->4.初始化友推并调用

4.1 引用友推库项目

将youtui-lib项目库和应用工程放在同一个目录下

在 Package Explorer 中右键点击工程的根目录，选择 Properties(属性)，然后点击，在Android选项点击Add添加youtui-lib

如下图所示：
![](http://youtui.mobi/doc/media/lib.jpg)

4.2注册需要分享的平台

1.配置各分享平台key，该配置文件为youtui_sdk.xml,配置完放入工程的assets文件夹。

2.如果需要分享到哪个平台就将该平台的Enable属性设置为true.

3.如果需要将某个平台排列到前面，只需要改变它在youtui_sdk.xml文件中的位置即可。

各平台需要注意的事项：

新浪微博：

新浪微博需要验证应用签名，请一定要在新浪开放平台管理中心应用信息 -> 基本信息 -> Android签名包名信息 配置该信息

每次包名变化或者使用的.keystore变化都会导致应用签名变化，请重新到新浪微博开放平台设置。

运行Demo时如果重新编译，因为使用的.keystore文件变化也会导致应用签名变化，导致Demo新浪微博分享无法正常工作，请运行Demo时使用Demo工程包中提供的debug.keystore替换C:\Users\Administrator\.android中的debug.keystore文件

微信和朋友圈：

微信和朋友圈也需要验证应用签名，请在微信开放平台管理中心修改应用 -> 开发信息配置。

QQ和QQ空间：

QQ和QQ空间使用的是腾讯开放平台api，请在腾讯开放平台申请账号和注册应用，请不要使用QQ互联（用于网站账号登录）的配置，虽然都是腾讯的，但是两个平台并不通用，除了需要在youtui_sdk.xml配置信息，还需要在manifest文件中的android:scheme中的tencent后的一串数字换成自己应用的appid。

<?xml version="1.0" encoding="utf-8"?>
<KeyInfo>
<!-- 分享平台的注册信息，一定要填入在相应平台注册的正确信息，不然应用无法完成授权，  也无法进行分享，enable填写true或者false属性决定是否分享该平台-->
<!-- 友推sdk注册地址 : http://youtui.mobi/ -->
<YouTui AppKey="" />

<!-- 微信和朋友圈注册:https://open.weixin.qq.com/ -->
<Wechat AppId="" Enable="" />
<WechatMoments AppId="" Enable="" />

<!-- 新浪微博注册地址:http://open.weibo.com/ -->
<SinaWeibo AppKey="" AppSecret="" Enable="" RedirectUrl="" />

<!-- QQ,QQ空间,腾讯微博注册地址:http://open.qq.com/ -->
<QQ AppId="" AppKey="" Enable="" />
<QZone AppId="" AppKey="" Enable="" />
<!-- 腾讯微博注册地址:http://dev.t.qq.com/ -->
<TencentWeibo AppKey="" AppSecret="" Enable="" RedirectUrl="" />

<!-- 人人注册地址: http://dev.renren.com/ -->
<Renren AppKey="" AppId="" Enable="" SecretKey="" />
<ShortMessage Enable="" />
<Email Enable="" />
<!-- 调用系统分享，适用于暂时没有申请到key的分享，该分享不会获得积分，也不会被统计到 -->
<More Enable="true"/>
</KeyInfo>

                
最后一行配置说明：选用调用系统分享菜单功能（v2.1更新）

4.3 在AndroidManifest.xml 注册权限

<!-- 检测网络状态 -->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 
<!-- 获取mac地址作为用户的备用唯一标识 -->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
 
<!-- 获取用户手机的IMEI，用来唯一的标识用户。 -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
 
<!-- 写入SDcard权限 -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 
<!--打开关闭sd卡权限--!>
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
 
<!--网络权限--!>
<uses-permission android:name="android.permission.INTERNET" />
 
<!-- 用于读取sd卡图片 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 
<!-- 用于人人SSO登陆 -->
<uses-permission android:name="android.permission.GET_ACCOUNTS" />
 
<!-- 用于人人SSO登陆 -->
<uses-permission android:name="android.permission.USE_CREDENTIALS" />
 
<!-- 用于人人SSO登陆 -->
<uses-permission android:name="android.permission.MANAGE_ACCOUNTS" />
 
4.4在 AndroidManifest.xml 注册需要的Activity

<!-- 微信分享需要注册该activity -->
<activity
android:name=".wxapi.WXEntryActivity"
android:exported="true"
android:launchMode="singleTask"
android:theme="@android:style/Theme.Translucent" >
</activity>

<!-- qq回调需要注册该activity -->
<activity
android:name="com.tencent.connect.common.AssistActivity"
android:configChanges="orientation|keyboardHidden"
android:screenOrientation="portrait"
android:theme="@android:style/Theme.Translucent.NoTitleBar" />

<!-- qq授权需要注册该activity -->
<activity
android:name="com.tencent.tauth.AuthActivity"
android:launchMode="singleTask"
android:noHistory="true" >
<intent-filter>
<action android:name="android.intent.action.VIEW" />
<category android:name="android.intent.category.DEFAULT" />
<category android:name="android.intent.category.BROWSABLE" />

<!-- 请将1101255276换成开发者自己应用的腾讯开放平台 Appid-->
<data android:scheme="tencent1101255276" />
</intent-filter>
</activity>

<!-- 人人授权需要注册的activity -->
<activity
android:name="com.renn.rennsdk.oauth.OAuthActivity"
android:configChanges="orientation|navigation|keyboardHidden" />

<!-- 新浪微博分享回调需要设置 -->
<intent-filter>
<action android:name="com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY" />
<category android:name="android.intent.category.DEFAULT" />
</intent-filter>
</activity>

<!-- 分享界面 -->
<activity
android:name="cn.bidaround.ytcore.activity.ShareActivity"
android:exported="true"
android:launchMode="singleTop"
android:theme="@android:style/Theme.Translucent.NoTitleBar" />
<!-- 应用授权 activity-->
<activity
android:name="cn.bidaround.ytcore.login.AuthActivity"
android:theme="@android:style/Theme.Translucent.NoTitleBar" />
<!-- 友推积分activity -->
<activity
android:name="cn.bidaround.point.PointActivity" />
<!-- 友推渠道号，应用名(英文或拼音)+"_yt"，如：“jd_yt”，用于识别通过友推下载的应用，请正确填写否则无法正确统计 -->
<meta-data
android:name="YOUTUI_CHANNEL"
android:value="yourappname_yt" >
</meta-data>

4.5微信和朋友圈回调设置

如果需要分享微信和朋友圈，必需建一个 应用包名+ .wxapi 的包，在该包下建 WXEntryActivity.java，将该类继承cn.bidaround.youtui.wxapi.WXEntryActivity即可(里面不用写代码)

public class WXEntryActivity extends cn.bidaround.youtui.wxapi.WXEntryActivity {
}
如图：（将com.xingxinglangtuoche替换成你应用的package名，微信回调会使用到）

![](http://youtui.mobi/doc/media/weixin_share.jpg)

4.6初始化友推

开发者请在自己的程序开始，最好是在MainActivity的onCreate方法调用YtTemplate.init(this)初始化友推sdk，这样友推sdk才能进行后续调用(否则分享等操作会出现空指针异常),例如:

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    YtTemplate.init(this);/*初始化友推*/
    initView();
}
应用退出时：

在您项目的出口Activity的 onDestroy 方法的第一行插入下面的代码 YtTemplate.release(this); 此方法用于释放内存，统计用户使用情况，一旦调用了release，就必须重新调用init才能使用友推的功能，否则会出现空指针异常;

 

4.6创建 ShareData 实例，调用该实例的set方法设置需要分享的数据：
创建ShareData实例，调用该实例的set方法设置自己需要分享的数据，关于该实例具体内容见下文，如果只是分享应用则只需要设置 setIsAppShare(true) 就可以分享应用在友推后台填写的信息和下载链接。

ShareData 包含的字段：

isAppShare
text
imagePath
imageUrl
description
title
target_url
判断是否为分享应用
待分享的文字，短信要小于70个字符，微博要小于140个字符，如果需要分享链接，最好将链接url放在最后
待分享的本地图片地址，分享图片的话需要在本地和网络图片中选一，如果都有则优先分享本地图片
待分享网络图片url，分享图片的话需要在本地和网络图片中选一，如果都有则优先分享本地图片
待分享内容的描述
待分享内容的标题
待分享内容的跳转链接

通过创建该类实例，调用实例的set方法设置这些参数，例如:

ShareData shareData = new ShareData();
shareData.isAppShare = false;//设置为true则分享的信息从友推后台填写的应用信息中读取，可动态更新，后面的值不用设置。
shareData.setDescription("友推积分组件");
shareData.setTitle("友推分享");
shareData.setText("通过友推积分组件，开发者几行代码就可以为应用添加分享送积分功能，并提供详尽的后台统计数据，除了本身具备的分享功能外，开发者也可将积分功能单独集成在已有分享组件的app上，快来试试吧 http://youtui.mobi");
shareData.setTarget_url("http://youtui.mobi");
shareData.setImageUrl("http://youtui.mobi/media/image/youtui.png");
shareData.setImagePath("http://cdnup.b0.upaiyun.com/media/image/default.png");

各个平台分享数据的限制和注意事项：

因为各个平台的分享限制，请分享时尽量分享图片+链接，依靠链接来了解更多信息

1) 微信 朋友圈
微信分享为linkcard形式，超出的文字部分不会显示

2) 新浪微博
很低版本的新浪微博不支持发多类型微博，进行图文分享时只会分享图片，新浪微博分享消息最长为140字

3) QQ、QQ空间
QQ分享的消息最长40字，分享的标题最长30字，多余的部分将被忽略，description将被忽略
QQ空间分享的消息最长200字，分享的标题最长600字，多余的部分将被忽略，description将被忽略

4) 腾讯微博
只有image(imagePath或imageUrl)和text被分享，其他字段忽略，腾讯微博分享消息最长为140字

5) 人人网
只有image(imagePath或imageUrl)和text被分享，其他字段忽略

6) 短信
只有text被分享，其他字段忽略

7) 邮件
只有text被分享，其他字段忽略

 

4.8调用友推分享推荐组件
![](http://youtui.mobi/doc/media/how_to_call.png)

为应用添加一个分享推荐按钮，如：
![](http://youtui.mobi/doc/media/example1.png) ![](http://youtui.mobi/doc/media/example2.png) ![](http://youtui.mobi/doc/media/example3.png)

图：分享推荐按钮放置位置示例

在分享按钮事件中调用youtui的组件即可，示例代码：

public void onClick(View v) {
    if(v.getId()==R.id.popup_bt){
        /*调用友推分享推荐组件,YouTuiViewType类的常量为分享样式参数，目前支持白色列表和黑色网格两种*/
        /*创建分享的模板，第一个参数为activity,第二个参数为分享窗口样式，第三个参数为是否需要积分*/
        YtTemplate blackTemp = new YtTemplate(this, YouTuiViewType.BLACK_POPUP,false);  //黑色网格样式不需要积分活动
        /*YtTemplate blackTemp = new YtTemplate(this, YouTuiViewType.WHITE_LIST,ture);*/ //白色列表样式需要积分活动
        
	ShareData shareData = new ShareData();
	shareData.isAppShare = false;//设置为true则分享的信息从友推后台填写的应用信息中读取,可动态更新后面的值不用设置。
	shareData.setDescription("友推积分组件");
	shareData.setTitle("友推分享");
	shareData.setText("通过友推积分组件，开发者几行代码就可以为应用添加分享送积分功能，并提供详尽的后台统计数据，除了本身具备的分享功能外，开发者也可将积分功能单独集成在已有分享组件的app上，快来试试吧 http://youtui.mobi");
	shareData.setTarget_url("http://youtui.mobi");
	shareData.setImageUrl("http://youtui.mobi/media/image/youtui.png");
	shareData.setImagePath("http://cdnup.b0.upaiyun.com/media/image/default.png");

	blackTemp.setShareData(shareData);//设置默认的分享数据;shareData 设置参看4.6
	//**如果要为某个平台设置不一样的分享信息。则单独设置*/
	//blackTemp.addData(YtPlatform.PLATFORM_QQ, shareData);
	//调出分享窗口
	blackTemp.show();
		
	//如果需要自定义分享事件，可以创建监听事件，然后在回调中处理
	YtShareListener listener1 = new YtShareListener() {			
		@Override
		public void onSuccess(ErrorInfo arg0) {
				
		}
			
		@Override
		public void onPreShare() {
				
		}
			
		@Override
		public void onError(ErrorInfo arg0) {
				
		}
			
		@Override
		public void onCancel() {
				
		}
	};
	//给新浪微博添加分享监听
	blackTemp.addListener(YtPlatform.PLATFORM_SINAWEIBO, listener1);
	//给QQ添加分享监听
	//blackTemp.addListener(YtPlatform.PLATFORM_QQ, listener2);
	
	}
}
这样就成功集成了友推的分享推荐功能了，用户点击推荐分享按钮后，界面如下：demo下载 （android）

![](http://youtui.mobi/doc/media/demo-white.png) ![](http://youtui.mobi/doc/media/demo-black.png)

应用可以在后台设置分享送积分、积分兑换礼品活动，激励用户跟主动向好友分享您的应用，以及查看用户分享推荐的数据明细。

![](http://youtui.mobi/doc/media/demo-jifeng.png) ![](http://youtui.mobi/doc/media/demo-draw.png) ![](http://youtui.mobi/doc/media/demo-duihuan.png)

如果没有设置分享送积分活动则不会提示分享送积分活动信息，返回到基本的分享组件功能。

案例：兼职猫集成后，用户通过QQ分享给好友，点击链接进入应用简介下载页面
![](http://youtui.mobi/doc/media/jianzhimao.png) ![](http://youtui.mobi/doc/media/qq.png) ![](http://youtui.mobi/doc/media/download.png)

应用可以在后台设置推广活动及查看用户分享的明细报表。

![](http://youtui.mobi/doc/media/promote.png)
![](http://youtui.mobi/doc/media/backstage.png)
图：后台积分分享数据统计报表页面

4.9常见集成错误

1) v4包版本冲突，解决方法在lib工程和应用工程使用同一个版本的v4包

2) 社交平台jar包冲突，如果开发者前面引入过分享平台的开发包，并且和友推sdk使用的开发包冲突，请删除原先导入的包

3) youtui_sdk.xml填写错误，导致解析异常，无法读取开发者注册信息，请务必正确填写

4) 新浪微博分享时显示出错了或sso package or sign error，请检查在新浪申请的应用Android签名设置是否正确，当使用的keystore变化时，注意到新浪开发平台修改应用签名

4.10避免代码混淆造成错误

//微信：
-keep class com.tencent.mm.sdk.openapi.WXMediaMessage { *;}
-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}

//qq和qq空间:
-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}

5.第三方登录
5.1初始化友推

开发者请在自己的程序开始，最好是在MainActivity的onCreate方法调用YtTemplate.init(this)初始化友推sdk，这样友推sdk才能进行后续调用:

如果此前调用友推分享组件已初始化则不用重复。

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    YtTemplate.init(this);/*初始化友推*/
    initView();
}
5.2注册Activity

<!-- 调用友推第三方登录需要注册该activity -->
<activity android:name="cn.bidaround.ytcore.login.AuthActivity"
	  android:theme="@android:style/Theme.Translucent.NoTitleBar" />
	  
5.3创建授权类实例

创建一个AuthLogin实例，并创建监听器listener，授权完成后的页面跳转等操作请放在这里,例如:

AuthLogin auth  = new AuthLogin();
	  
AuthListener listener = new AuthListener() {
	@Override
	public void onAuthSucess(Activity act, AuthUserInfo userInfo) {
		Log.i("sinaGender:", userInfo.getSinaGender());
		Log.i("sinaName:", userInfo.getSinaName());
		//获取新浪用户头像url
		Log.i("sinaProfileImageUrl:", userInfo.getSinaProfileImageUrl());
		//获取新浪用户昵称
		Log.i("sinaScreenname:", userInfo.getSinaScreenname());
		Log.i("sinaUid:", userInfo.getSinaUid());
		// 模拟授权成功后跳转到用户界面
		Intent sinaIt = new Intent(MainActivity.this, UserActivity.class);
		sinaIt.putExtra("from", "sina");
		sinaIt.putExtra("sinaGender", "sinaGender:" + userInfo.getSinaGender());
		sinaIt.putExtra("sinaName", "sinaName:" + userInfo.getSinaName());
		sinaIt.putExtra("sinaProfileImageUrl", userInfo.getSinaProfileImageUrl());
		sinaIt.putExtra("sinaScreenname", "sinaScreenname:" + userInfo.getSinaScreenname());
		sinaIt.putExtra("sinaUid", "sinaUid:" + userInfo.getSinaUid());
		act.startActivityForResult(sinaIt, 0);
	}

	@Override
	public void onAuthFail(Activity act) {
	}

	@Override
	public void onAuthCancel(Activity act) {
	}

};
auth.sinaAuth(this, listener);//调用新浪微博授权登录
//auth.qqAuth(this, qqListener);//调用QQ授权登录

5.4调用授权类的授权方法

例如：auth.sinaAuth(this, listener);//使用新浪微博授权登录

平台	方法
新浪微博授权登录	auth.sinaAuth(this, listener);
qq授权登录	auth.qqAuth(this, qqListener);
腾讯微博授权登录	auth.tencentWbAuth(this, tencentWbListener);
其中参数1为当前Activity,参数2为授权监听，需要实现AuthListener接口,该接口中act为授权Activity,userInfo对象中携带授权后获得的用户信息，使用get方法可以获取:

5.5进行授权后的操作

请将授权后的操作写在AuthListener对象的方法中，onAuthSucess方法处理授权成功操作，onAuthFail处理授权失败，onAuthCancel处理授权取消（见上）

5.6获取授权用户信息

在AuthListener的onAuthSucess方法第二个参数userInfo中携带有授权用户信息，使用get获取

名称	方法
新浪用户性别	userInfo.getSinaGender()
新浪用户姓名	userInfo.getSinaName()
新浪用户头像url	userInfo.getSinaProfileImageUrl()
新浪用户昵称	userInfo.getSinaScreenname()
新浪用户id	userInfo.getSinaUid()
qq用户性别	userInfo.getQqGender()
qq用户头像url	userInfo.getQqImageUrl()
qq用户昵称	userInfo.getQqNickName()
qq用户openid	userInfo.getQqOpenid()
腾讯微博用户姓名	userInfo.getTencentWbName()
腾讯微博用户昵称	userInfo.getTencentWbNick()
腾讯微博用户openid	userInfo.getTencentWbOpenid()
腾讯微博用户头像url(注意这不是一个完整的url，请在后面添加/尺
寸得到完整url，比如加上/40得到尺寸为40的头像url)	userInfo.getTencentWbHead()
腾讯微博用户性别	userInfo.getTencentWbGender()
6. 联系信息：

友推后续将陆续完善及推出更多功能，致力于应用流量及用户粘性环节，让用户更主动更方便推荐您的应用！

如使用过程中有任何问题或需求，欢迎与我们联系，友推将竭诚为您服务。

邮箱：kefu@bidaround.cn

QQ：1030311324

固话：020-29042527

电话：13760605982（创始人steven）

微博：@碧周友推


 




===============
