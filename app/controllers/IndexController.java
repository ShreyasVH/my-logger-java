package controllers;

import com.google.inject.Inject;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import models.Log;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;
import services.IndexService;
import skeletons.requests.FilterRequest;
import skeletons.requests.SaveLogRequest;
import utils.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class IndexController extends BaseController
{
	private final IndexService indexService;

	private final HttpExecutionContext httpExecutionContext;

	@Inject
	public IndexController(
		IndexService indexService,
		HttpExecutionContext httpExecutionContext
	)
	{
		this.indexService = indexService;
		this.httpExecutionContext = httpExecutionContext;
	}

	public CompletionStage<Result> index()
	{
		return performAsync(indexService.index()).thenApplyAsync(response -> ok(Json.toJson(response)));
	}

	public CompletionStage<Result> getWithFilters()
	{
		return CompletableFuture.supplyAsync(() -> {
			FilterRequest request = null;

			try
			{
				request = Utils.convertObject(request().body().asJson(), FilterRequest.class);
			}
			catch (Exception ex)
			{
				String sh = "sh";
			}

			return indexService.getWithFilters(request);
		}, httpExecutionContext.current()).thenApplyAsync(response -> ok(Json.toJson(response)), httpExecutionContext.current());
	}

	public CompletionStage<Result> saveLog()
	{
		return CompletableFuture.supplyAsync(() -> {
			SaveLogRequest request = null;

			try
			{
				request = Utils.convertObject(request().body().asJson(), SaveLogRequest.class);
			}
			catch (Exception ex)
			{
				String sh = "sh";
			}

			return indexService.saveLog(request);
		}, httpExecutionContext.current()).thenApplyAsync(response -> ok(Json.toJson(response)), httpExecutionContext.current());
	}
}