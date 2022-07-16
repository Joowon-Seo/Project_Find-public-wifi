<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <style>
        table {
            width: 100%;
            margin: auto;
        }
        table, th, td {
            border: 1px solid #bcbcbc;
            text-align: center;
        }
        tr:nth-child(odd) { background-color: #e6f1ff; }
        tr:nth-child(even) { background-color: #f0f7ff; }
        tr:hover { background-color: #ffc5c2; cursor: pointer; }
    </style>
    <h1><%= "와이파이 정보 구하기" %></h1>
    <a href="index.jsp">홈 | </a>
    <a href="history.jsp">위치 히스토리 목록 | </a>
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <p></p>
</head>
<body>
<script>
    var latitude
    var longitude
    function search(){
            navigator.geolocation.getCurrentPosition(function(pos) {
            latitude = pos.coords.latitude;
            longitude = pos.coords.longitude;
            document.getElementById("latitude").value = latitude;
            document.getElementById("longitude").value = longitude;
        })}
</script>
    <form method="get" action="result.jsp">
        LAT:<input type='text' id="latitude" name="latitude"/>
        LNT:<input type='text' id="longitude" name="longitude"/>
        <input type = "button" value="내 위치 가져오기" onclick='search()'>
        <button type = "submit">근처 WIFI 정보 보기</button>
    </form>
    <br>
    <table>
        <thead>
        <tr>
            <th>거리(km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody>
        <tr>

            <td style="text-align:center" colspan="17">위치 정보를 입력한 후에 조회해 주세요,</td>
        </tr>

        </tbody>
    </table>
</body>
</html>