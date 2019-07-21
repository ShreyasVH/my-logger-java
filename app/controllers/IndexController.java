package controllers;

import play.mvc.Result;
import play.mvc.Results;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class IndexController extends BaseController
{
	public CompletionStage<Result> index()
	{
		return CompletableFuture.completedFuture("INDEX").thenApplyAsync(Results::ok);
	}
}