package com.github.tianjing.example.empty.webapp.demo.gateway;

//import tgtools.spirngbootweb.common.config.ActivitiConfig;
//
///**
// * @author 田径
// * @Title
// * @Description
// * @date 18:57
// */
//@RequestMapping("demo/treeview")
//@Controller
//public class TreeView {
//
//    @Autowired
//    ActivitiConfig activitiConfig;
//
//    @RequestMapping(value = "/data/list")
//    @ResponseBody
//    public JsonNode getTree()
//    {
//        ObjectMapper mapper =new ObjectMapper();
//        try {
//            String res = activitiConfig.flowTreeViewService().getTree("田径3", "tg:2:e71ed6a9-205f-11e8-9b9d-1c3947ccbbc7", "", "");
//            return mapper.readTree(res);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return mapper.createArrayNode();
//        }
//    }
//}
