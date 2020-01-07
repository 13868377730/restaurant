package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.ex.FoodSales;
import com.briup.restaurant.service.IFoodService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.CellType;
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
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/food")
@Api(description = "菜单管理")
public class FoodController {
    @Autowired
    private IFoodService ifoodService;

    @PostMapping("/addFood")
    @ApiOperation("添加菜品")
    public Message addFood(Food food){
ifoodService.addOrUpdateFood(food);
        return MessageUtil.success("添加成功");
    }
    @PostMapping("/updateFood")
    @ApiOperation("修改菜品")
    public Message updateFood(Food food){
        ifoodService.addOrUpdateFood(food);
        return MessageUtil.success("修改成功");
    }

    @GetMapping("/selectAll")
    @ApiOperation("查询所有菜品信息")
   public Message selectAll(){
        return MessageUtil.success(ifoodService.selectAll());
   }
    @GetMapping("/selectById")
    @ApiOperation("根据id菜品信息")
    public Message selectByName(int id)
    {
        return MessageUtil.success(ifoodService.selectById(id));
    }


    @GetMapping("/selectBy")
    @ApiOperation("根据菜名，菜品类型，菜品的状态查询菜品信息")
    @ApiImplicitParams({@ApiImplicitParam(name= "key1",value="菜品类型 热菜，凉菜，饮品，汤，主食",paramType = "query" ,dataType = "String"),
@ApiImplicitParam(name= "key2",value="菜品状态 在架，下架",paramType = "query" ,dataType = "String")
    })
    public Message selectBy(String key1,String key2,String word){
        List<Food> foods=  ifoodService.selectBy(key1,key2,word);
        return MessageUtil.success(foods);
    }
@GetMapping("/selectByState")
@ApiOperation("查询所有在架菜品")
public Message selectByState(){

        return MessageUtil.success(ifoodService.selectByState());
}
    @GetMapping("/selectSales")
    @ApiOperation("查看菜品销量")
    @ApiImplicitParams({@ApiImplicitParam(name= "date1",value="开始日期 YYYY-MM-dd",paramType = "query" ,dataType = "String"),
            @ApiImplicitParam(name= "date2",value="结束日期 YYYY-MM-dd",paramType = "query" ,dataType = "String")
    })
    public Message selectSales(String date1,String date2){
        List<FoodSales> foodSales=  ifoodService.selectSales(date1, date2);
        return MessageUtil.success(foodSales);
}

    @GetMapping("/selectMonth")
    @ApiOperation("查看当前月份菜品销量")
    public Message selectMonth(){
        return MessageUtil.success(ifoodService.selectMonth());
    }
    @GetMapping("/delectById")
    @ApiOperation("删除")
    @ApiImplicitParam(name= "id",value="菜品编号",paramType = "query" ,dataType = "String" ,required=true)
    public Message delectById(int id){
        ifoodService.deleteById(id);
        return  MessageUtil.success("操作成功");
  }
@GetMapping("/download")
    @ApiOperation("下载菜单")
    public void download(HttpServletResponse response)throws IOException {
 List<Food> foods= ifoodService.selectByState();
    //创建工作簿
    XSSFWorkbook workbook = new XSSFWorkbook();
    //创建工作表格
    XSSFSheet sheet=workbook.createSheet("全部菜单");
    //创建行
    XSSFRow row=sheet.createRow(0);
    //创建列 创建单元格
    XSSFCell cell=row.createCell(0);
    //设置单元格数据类型
    cell.setCellType(CellType.STRING);
    XSSFRow row1 = sheet.createRow(0);
    row1.createCell(0).setCellValue("菜名");
    row1.createCell(1).setCellValue("价钱");
    row1.createCell(2).setCellValue("类型");
    row1.createCell(3).setCellValue("状态");
    for (int i=0;i<foods.size();i++){
        XSSFRow rown = sheet.createRow(1+ i);
        rown.createCell(0).setCellValue(foods.get(i).getName());
        rown.createCell(1).setCellValue(foods.get(i).getPrice());
        rown.createCell(2).setCellValue(foods.get(i).getType());
        rown.createCell(3).setCellValue(foods.get(i).getState());

    }
    // 告诉浏览器用什么软件可以打开此文件
    response.setHeader("content-Type", "application/vnd.ms-excel");
    // 下载文件的默认名称
    response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("全部菜单"+".xlsx", "utf-8"));

    try {
        workbook.write(response.getOutputStream());
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
