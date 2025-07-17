package br.com.ourogourmet.application.presenter;

public interface Presenter<TInput, TOutput> {
    TOutput presenter(TInput response);
}