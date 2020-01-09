package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.Item;
import com.briup.restaurant.bean.ex.ItemEX;
import com.briup.restaurant.mapper.OrderMapper;
import com.briup.restaurant.mapper.ex.ItemEXMapper;
import com.briup.restaurant.service.IOrderManageService;
import com.briup.restaurant.service.IOrderSettleService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/order/manage")
@Api(description = "订单管理")
public class OrderManageController {

    @Autowired
    private IOrderManageService iOrderManageService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ItemEXMapper itemEXMapper;



    @GetMapping("/selectall")
    @ApiOperation(value = "查询所有订单")
    public Message selectAll(){
        return MessageUtil.success(iOrderManageService.selectAll());

    }

    @GetMapping("/selectdetailbyid")
    @ApiOperation(value = "查看订单详细信息")
    public Message selectDetailById(int id){
        return MessageUtil.success(iOrderManageService.showDetailById(id));
    }

    @PostMapping("/updatetablebyid")
    @ApiOperation(value = "更换餐桌")
    public Message updateTableById(int orderId, int tableId){
        iOrderManageService.updateTableById(orderId,tableId);
        return MessageUtil.success("操作成功");
    }

    @PostMapping("/discountbyid")
    @ApiOperation(value = "打折")
    public Message discountById(int orderId, double price){
        iOrderManageService.discountById(orderId,price);
        return MessageUtil.success("操作成功");
    }
    @GetMapping("/deletefoodbyid")
    @ApiOperation(value = "取消未上桌菜品")
    public Message deleteFoodById(int orderId, int foodId){
        iOrderManageService.deleteFoodById(orderId,foodId);
        return MessageUtil.success("操作成功");
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索")
    public Message searchByCon(String key, String word){
        return MessageUtil.success(iOrderManageService.searchByCon(key,word));
    }
    @GetMapping("/deleteorder")
    @ApiOperation(value = "取消订单")
    public Message deleteOrderById(int id){
        iOrderManageService.deleteOrderById(id);
        return MessageUtil.success("操作成功");
    }

    @ApiOperation("通过id下载")
    @GetMapping("/downout")
    public void downout(HttpServletResponse response, int id)throws UnsupportedEncodingException {
        Map<String, Object> stringObjectMap = iOrderManageService.showDetailById(id);
        List<ItemEX> Items = itemEXMapper.findById(id);

        if("外卖".equals(orderMapper.selectByPrimaryKey(id).getType())){
            //创建工作簿
            XSSFWorkbook workbook = new XSSFWorkbook();
            //创建工作表格
            XSSFSheet sheet=workbook.createSheet("订单详情");
            //创建行
            XSSFRow row=sheet.createRow(0);
            //创建列 创建单元格
            XSSFCell cell=row.createCell(0);
            //设置单元格数据类型
            cell.setCellType(CellType.STRING);
            cell.setCellValue("订单编号");
            XSSFCell cell1_2 = row.createCell(1);
            cell1_2.setCellValue("姓名");
            XSSFCell cell1_3 = row.createCell(2);
            cell1_3.setCellValue("联系方式");
            XSSFCell cell1_4 = row.createCell(3);
            cell1_4.setCellValue("地址");
            row.createCell(4).setCellValue("总金额");
            row.createCell(5).setCellValue("状态");

            //第二行
            XSSFRow row2 = sheet.createRow(1);
            row2.createCell(0).setCellValue(stringObjectMap.get("id").toString());
            row2.createCell(1).setCellValue(stringObjectMap.get("name").toString());
            row2.createCell(2).setCellValue(stringObjectMap.get("phone").toString());
            row2.createCell(3).setCellValue(stringObjectMap.get("address").toString());
            row2.createCell(4).setCellValue(stringObjectMap.get("price").toString());
            row2.createCell(5).setCellValue(stringObjectMap.get("state").toString());

            //第三行
            XSSFRow row3 = sheet.createRow(2);
            row3.createCell(0).setCellValue("备注");
            //合并单元格
            sheet.addMergedRegion(new CellRangeAddress(2,2,1,5));
            //设置单元格内容
            row3.createCell(1).setCellValue(stringObjectMap.get("remark").toString());

            //第五行
            XSSFRow row4 = sheet.createRow(4);
            sheet.addMergedRegion(new CellRangeAddress(4,4,0,2));
            row4.createCell(0).setCellValue("菜品列表");

            //第六行
            XSSFRow row5 = sheet.createRow(5);
            row5.createCell(0).setCellValue("名称");
            row5.createCell(1).setCellValue("价钱");
            row5.createCell(2).setCellValue("状态");

            //插入数据
            for (int i=0;i<Items.size();i++){
                XSSFRow rown = sheet.createRow(6 + i);
                rown.createCell(0).setCellValue(Items.get(i).getFood().getName());
                rown.createCell(1).setCellValue(Items.get(i).getFood().getPrice());
                rown.createCell(2).setCellValue(Items.get(i).getState());
            }
            




            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称


            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("订单详情.xlsx", "utf-8"));
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        if ("堂食".equals(orderMapper.selectByPrimaryKey(id).getType())){
            //创建工作簿
            XSSFWorkbook workbook = new XSSFWorkbook();
            //创建工作表格
            XSSFSheet sheet=workbook.createSheet("订单详情");
            //创建行
            XSSFRow row=sheet.createRow(0);
            //创建列 创建单元格
            XSSFCell cell=row.createCell(0);
            //设置单元格数据类型
            cell.setCellType(CellType.STRING);
            cell.setCellValue("订单编号");
            XSSFCell cell1_2 = row.createCell(1);
            cell1_2.setCellValue("餐桌号");
            XSSFCell cell1_3 = row.createCell(2);
            cell1_3.setCellValue("状态");
            XSSFCell cell1_4 = row.createCell(3);
            cell1_4.setCellValue("总金额");

            //第二行
            XSSFRow row2 = sheet.createRow(1);
            row2.createCell(0).setCellValue(stringObjectMap.get("id").toString());
            row2.createCell(1).setCellValue(stringObjectMap.get("tableId").toString());
            row2.createCell(2).setCellValue(stringObjectMap.get("state").toString());
            row2.createCell(3).setCellValue(stringObjectMap.get("price").toString());

            //第三行
            XSSFRow row3 = sheet.createRow(2);
            row3.createCell(0).setCellValue("备注");
            //合并单元格
            sheet.addMergedRegion(new CellRangeAddress(2,2,1,3));
            //设置单元格内容
            row3.createCell(1).setCellValue(stringObjectMap.get("remark").toString());

            //第五行
            XSSFRow row4 = sheet.createRow(4);
            sheet.addMergedRegion(new CellRangeAddress(4,4,0,2));
            row4.createCell(0).setCellValue("菜品列表");

            //第六行
            XSSFRow row5 = sheet.createRow(5);
            row5.createCell(0).setCellValue("名称");
            row5.createCell(1).setCellValue("价钱");
            row5.createCell(2).setCellValue("状态");

            //插入数据
            for (int i=0;i<Items.size();i++){
                XSSFRow rown = sheet.createRow(6 + i);
                rown.createCell(0).setCellValue(Items.get(i).getFood().getName());
                rown.createCell(1).setCellValue(Items.get(i).getFood().getPrice());
                rown.createCell(2).setCellValue(Items.get(i).getState());
            }





            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称


            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("订单详情.xlsx", "utf-8"));
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/code")
    @ApiOperation(value = "根据id生成或更新订单二维码")
    public Message addOrUpaQRCodeById(int id) throws JsonProcessingException {


        iOrderManageService.addOrUpdQRCodeById(id);

        return MessageUtil.success("操作成功");
    }
}
