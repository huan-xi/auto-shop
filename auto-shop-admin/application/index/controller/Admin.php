<?php
/**
 * Created by IntelliJ IDEA.
 * User: huanxi
 * Date: 2018/12/1
 * Time: 15:56
 */

namespace app\index\controller;


use app\index\model\Device;
use think\Controller;

class Admin extends Controller
{
    public function index()
    {
        return "admin page";
    }

    public function device()
    {
        $data = input("post.");
        if (request()->isPost()) {
            if((new Device($data))->save())
                json_return_msg(1,"添加成功");
            json_return_msg(0,"添加失败");
        }
        return $this->fetch();
    }
}
