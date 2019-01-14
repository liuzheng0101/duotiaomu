package com.example.lib_core.base.mvp;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M,V> {
    public M model;
    public V view;
    private WeakReference<V> weakReference;
    public abstract M getModel();
    public void attach(M model,V view){
        this.model=model;
        weakReference=new WeakReference<>(view);
        this.view=weakReference.get();
    }
    public void dettach(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference=null;
            this.view=null;
        }
    }
}
