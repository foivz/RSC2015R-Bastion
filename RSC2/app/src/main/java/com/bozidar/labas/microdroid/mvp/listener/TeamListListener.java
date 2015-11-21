package com.bozidar.labas.microdroid.mvp.listener;

import com.bozidar.labas.microdroid.mvp.model.CreatedTeamModel;

import java.util.List;

/**
 * Created by macbook on 18.10.2015..
 */
public interface TeamListListener {
    void onSuccess(List<CreatedTeamModel> model, String token);
}
