<?php
// +----------------------------------------------------------------------
// | ThinkPHP [ WE CAN DO IT JUST THINK ]
// +----------------------------------------------------------------------
// | Copyright (c) 2006-2016 http://thinkphp.cn All rights reserved.
// +----------------------------------------------------------------------
// | Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
// +----------------------------------------------------------------------
// | Author: 流年 <liu21st@gmail.com>
// +----------------------------------------------------------------------

// 应用公共文件
function json_return($data)
{
    exit(json_encode($data, 320));
}
function json_return_msg($status,$msg)
{
    json_return(['status'=>$status,'msg'=>$msg]);
}
//密码加密
function encrypt($str)
{
    return md5(md5("AUTH_CODE") . $str);
}
