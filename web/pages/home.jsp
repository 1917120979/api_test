<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--page-header  -->
<div>
	<div class="col-lg-12">
		<div class="page-header-p">统计</div>
	</div>
</div>
<!--统计概述  -->
<div>
	<div class="col-lg-4 col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-file fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">${pNum }</div>
						<div>项目数</div>
					</div>
				</div>
			</div>
			<div class="panel-footer">
				<a href="#" onclick="addProjectTab();return false;"> <span class="pull-left">查看详情</span>
					<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
				</a>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-4 col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-tasks fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">${apiNum }</div>
						<div>接口数</div>
					</div>
				</div>
			</div>
			<div class="panel-footer">
				<a href="#"> <span class="pull-left">查看详情</span> <span
					class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
				</a>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-4 col-md-6">
		<div class="panel panel-yellow">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-user fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">${userNum }</div>
						<div>用户数</div>
					</div>
				</div>
			</div>
			<div class="panel-footer">
				<a href="#"> <span class="pull-left">查看详情</span> <span
					class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
				</a>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>



