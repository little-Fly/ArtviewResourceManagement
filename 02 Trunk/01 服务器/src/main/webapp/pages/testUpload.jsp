<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
    <h2>文件上传</h2>
    <form action="../rs/file/add.do?json=%7B%22attrKey%22:%22RAt%E7%A4%BA%E4%BE%8B%E8%A1%A8%E5%A4%B4ID%22,%22attrName%22:%22%E7%A4%BA%E4%BE%8B%E8%A1%A8%E5%A4%B4%22,%22resourceKey%22:%22RDt%E7%A4%BA%E4%BE%8B%E5%80%BCID%22,%22typeKey%22:%22RDf%E7%A4%BA%E4%BE%8B%E8%A1%A8ID%22%7D" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>文件描述:</td>
                <td><input type="json" name="description"></td>
            </tr>
            <tr>
                <td>请选择文件:</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>
       <form action="../rs/file/add.do?json=%7B%22typeKey%22:%22RDf%E7%A4%BA%E4%BE%8B%E8%A1%A8ID%22%7D" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>文件描述2:</td>
                <td><input type="json" name="description"></td>
            </tr>
            <tr>
                <td>请选择文件2:</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>
    
</body>
</html>