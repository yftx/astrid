package com.todoroo.astrid.core;

import android.content.res.Resources;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.text.TextUtils;

import com.timsu.astrid.R;
import com.todoroo.andlib.utility.AndroidUtilities;
import com.todoroo.andlib.utility.DialogUtilities;
import com.todoroo.andlib.utility.TodorooPreferenceActivity;

public class PerformancePreferences extends TodorooPreferenceActivity {

    @Override
    public int getPreferenceResource() {
        return R.xml.preferences_performance;
    }

    @Override
    public void updatePreferences(Preference preference, Object value) {
        final Resources r = getResources();

        if (r.getString(R.string.p_swipe_lists_performance_key).equals(preference.getKey())) {
            preference.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference p, Object newValue) {
                    DialogUtilities.okDialog(PerformancePreferences.this, r.getString(R.string.EPr_swipe_lists_restart_alert), null);
                    return true;
                }
            });

            int index = 0;
            if(value instanceof String && !TextUtils.isEmpty((String)value))
                index = AndroidUtilities.indexOf(r.getStringArray(R.array.EPr_swipe_lists_performance_mode_values), (String)value);
            if (index < 0)
                index = 0;

            String name = r.getStringArray(R.array.EPr_swipe_lists_performance_mode)[index];
            String desc = r.getStringArray(R.array.EPr_swipe_lists_performance_desc)[index];
            preference.setSummary(r.getString(R.string.EPr_swipe_lists_display, name, desc));
        }
    }

}
