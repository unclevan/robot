## XX系统自动登录提交 ##

**使用说明** `使用请参考以下步骤`

- 一.基本配置准备（所有依赖参考pom坐标）
    # 其中chrome辅助工具chromedriver.exe（chromedriver.bak请改名）  请置于chrome.exe目录(例如：C:\Program Files (x86)\Google\Chrome\Application)
    
- 二.基本设计
    # CreateCookieService 使用selenium自动化工具javaAPI实现登录和会话保存（验证码码识别需要自助实现）
    # KeepConnectionAliveTask 定时刷新会话
    # SubmitService CSRFToken 与 com.zjjcnt.core.web.taglib.form.TOKEN 获取与表单提交