package br.com.ourogourmet.infrastructure.presenter;

public interface Presenter<TInput, TOutput> {
    TOutput presenter(TInput response);
}