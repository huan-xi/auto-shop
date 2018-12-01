<?php
/**
 * Created by IntelliJ IDEA.
 * User: huanxi
 * Date: 2018/10/4
 * Time: 19:45
 */
namespace app\index\model;
use think\Model;
class GoodBatchModel extends Model{

    protected $name='good_batch';
    protected $pk='batch_id';

    public function goods(){
        return $this->hasMany('GoodModel','good_id');
    }
}
