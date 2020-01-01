package com.yyf.demo.websocket;

import com.yyf.demo.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

/**
 * @author wangchen
 * @create 2019/12/14/13:07
 */
@RequestMapping("/pushMessage/")
@Controller
public class PushMessageController {

    //页面请求 用于跳转页面 通过页面上的js脚本与服务器建立websocket连接
    //通过model携带客户端标识 html页面中基于thymeleaf引擎 获取到cid
    @GetMapping("socket/{cid}")
    public String socket(@PathVariable String cid, Model model) {
        model.addAttribute("cid",cid);
        return "index";
    }

    /**
     * 推送数据接口 调用此接口可以给已连接的客户端推送消息
     * @param cid 可以为空 为空则表示给所有客户端推送数据 不为空给指定客户端推送数据
     * @param message 要推送的信息
     * @return
     */
    @ResponseBody
    @RequestMapping("socket/push")
    public JsonResult pushToWeb(@RequestParam(required = false) String cid,String message) {
        try {
            //调用sendInfo方法给客户端推送消息
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
            JsonResult result = new JsonResult(cid + "#" + e.getMessage());
            result.setState(0);
            return result;
        }
        return new JsonResult(cid+":"+message);
    }
}
