package controllers;

import play.mvc.Controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BaseController extends Controller
{
	public CompletionStage<Object> performAsync(Object object)
    {
        return CompletableFuture.completedFuture(object);
    }
}