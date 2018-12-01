<?php
/**
 * Created by IntelliJ IDEA.
 * User: huanxi
 * Date: 2018/12/1
 * Time: 14:01
 */

namespace app\index\controller;


use app\index\model\GoodBatchModel;
use app\index\model\GoodModel;
use app\index\model\GoodTypeModel;
use think\Config;
use think\Controller;

class Vender extends Controller
{
    public function index()
    {
        return "test";
    }

    /**
     * 添加商品分类
     */
    public function type()
    {
        if (request()->isPost()) {
            $data = input("post.");
            $goodType = new GoodTypeModel($data);
            //过滤
            if ($data['price'] <= 0)
                json_return_msg(0, "请输入合理的价格");
            if ($data['expiration_date'] <= 0)
                json_return_msg(0, "请输入合理的保证期");
            if ($goodType->save())
                return "添加成功";
            return "添加失败";
        }
        return $this->fetch();
    }

    /**
     * 添加商品批次
     */
    public function batch()
    {
        if (request()->post()) {
            $type_id = input("type_id/d");
            $count = input("count/d");
            var_dump($type_id);
            if (!$type_id)
                json_return_msg(0, "请选择合适的分类");
            if (!$count)
                json_return_msg(0, "请填写合适的数量");
            if ($count > 1000)
                json_return_msg(0, "每次不能生成超过一千个");
            $batch = new GoodBatchModel(['type_id' => $type_id, 'create_time' => time()]);
            $batch->save();
            for ($i = 0; $i < $count; $i++) {
                (new GoodModel(['batch_id' => $batch['batch_id'], 'status' => 1]))->save();
            }
            json_return_msg(1, "添加成功");
        }
        $types = GoodTypeModel::all();
        $this->assign("types", $types);
        return $this->fetch();
    }
}
