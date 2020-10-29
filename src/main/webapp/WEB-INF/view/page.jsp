<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Currency Converter</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
	<header>
		<h1><i>Конвертер валют</i></h1>
	</header>
	<section id="main-section">
		<div class="box" id="data-one">
			<div class="data-div">
				<h4><i>charcode</i></h4>
				<h4 class="data-div-value">${charcode_one}</h4>
			</div>
			<div class="data-div">
				<h4><i>name</i></h4>
				<h4 class="data-div-value">${name_one}</h4>
			</div>
			<div class="data-div">
				<h4><i>nominal</i></h4>
				<h4 class="data-div-value">${nominal_one}</h4>
			</div>
			<div class="data-div">
				<h4><i>value</i></h4>
				<h4 class="data-div-value">${value_one}</h4>
			</div>
		</div>

		<div class="box" id="main-box">
			<form class="main-box-div" id=top-div action="/" method="post">
				<div class="hor-div">
					<input class="input-number" placeholder="enter amount..." type="float" name="input_value"
						   value="${input_value}" pattern="\d+\.?\d*" required>
					<select class="input" multiple="multiple" name="charcode-one" id="select-curr-one" required>
						<c:forEach items="${currencies}" var="currencies">
							<option value="${currencies}">${currencies}</option>
						</c:forEach>
					</select>
				</div>
				<div class="hor-div" id="hor-div-mid">
					<button type="submit" id="hor-but-mid">-></button>
				</div>
				<div class="hor-div">
					<input class="input-number" type="text" value="${output}" readonly>
					<select class="input" multiple="multiple" name="charcode-two" required>
						<c:forEach items="${currencies}" var="currencies">
							<option value="${currencies}">${currencies}</option>
						</c:forEach>
					</select>
				</div>
			</form>
			<div class="main-box-div" id=bottom-div>

				<select id="history" multiple="multiple">
					<c:forEach items="${logs}" var="logs">
						<option value="${logs}">${logs}</option>
					</c:forEach>
				</select>

			</div>
		</div>

		<div class="box" id="data-two">
			<div class="data-div">
				<h4><i>charcode</i></h4>
				<h4 class="data-div-value">${charcode_two}</h4>
			</div>
			<div class="data-div">
				<h4><i>name</i></h4>
				<h4 class="data-div-value">${name_two}</h4>
			</div>
			<div class="data-div">
				<h4><i>nominal</i></h4>
				<h4 class="data-div-value">${nominal_two}</h4>
			</div>
			<div class="data-div">
				<h4><i>value</i></h4>
				<h4 class="data-div-value">${value_two}</h4>
			</div>
		</div>
	</section>
</body>
</html>