package com.nana.ELFramework.Event;

import java.io.Serializable;

public interface EventCallback extends Serializable {
    void onSuccess();
    void onError(String errorMsg);
}
