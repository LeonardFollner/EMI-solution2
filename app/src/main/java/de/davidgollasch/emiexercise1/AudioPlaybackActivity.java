package de.davidgollasch.emiexercise1;

import android.content.res.AssetFileDescriptor;
import android.graphics.RadialGradient;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.media.audiofx.PresetReverb;
import android.media.audiofx.Virtualizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.io.IOException;

public class AudioPlaybackActivity extends AppCompatActivity {

    private Switch sEasternEmotion, sReggaeFeeling;
    private MediaPlayer mpEasternEmotion, mpReggaeFeeling;

    private ToggleButton tbtnBassBoost, tbtnVirtualizer;

    private BassBoost bassBoost;
    private Virtualizer virtualizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_playback);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.tuAkzentfarbe1BlauHell)));

        InitializeActivity();
    }

    /**
     * Initialises widgets and event handlers
     */
    private void InitializeActivity() {
        sEasternEmotion = (Switch) findViewById(R.id.switchEasternEmotion);
        sReggaeFeeling = (Switch) findViewById(R.id.switchReggaeFeeling);

        sEasternEmotion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EasternEmotionToggled();
            }
        });

        sReggaeFeeling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ReggaeFeelingToggled();
            }
        });

        mpEasternEmotion = MediaPlayer.create(this, R.raw.eastern_emotion_terrasound_de);
        mpReggaeFeeling = MediaPlayer.create(this, R.raw.reggae_feeling_terrasound_de);

        mpEasternEmotion.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sEasternEmotion.setChecked(false);
            }
        });

        mpReggaeFeeling.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sReggaeFeeling.setChecked(false);
            }
        });

        tbtnBassBoost = (ToggleButton) findViewById(R.id.toggleButtonBassBoost);
        tbtnVirtualizer = (ToggleButton) findViewById(R.id.toggleButtonVirtualizer);

        tbtnBassBoost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BassBoostClicked();
            }
        });

        tbtnVirtualizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VirtualizerClicked();
            }
        });

    }

    /**
     * Handle toggling of Eastern Emotion switch
     */
    private void EasternEmotionToggled() {
        // set other switches unchecked
        if(sEasternEmotion.isChecked()) {
            sReggaeFeeling.setChecked(false);
        }

        if(sEasternEmotion.isChecked()) {
            PlaybackEasternEmotion();
        } else {
            PauseEasternEmotion();
        }
    }

    /**
     * Starts playback of first audio
     */
    private void PlaybackEasternEmotion() {
        ConfigureSoundEffects(mpEasternEmotion.getAudioSessionId());
        mpEasternEmotion.start();
    }

    /**
     * Halts playback of first audio
     */
    private void PauseEasternEmotion() {
        mpEasternEmotion.pause();
    }

    /**
     * Handle toggling of Reggae Feeling switch
     */
    private void ReggaeFeelingToggled() {
        // set other switches unchecked
        if(sReggaeFeeling.isChecked()) {
            sEasternEmotion.setChecked(false);
        }

        if(sReggaeFeeling.isChecked()) {
            PlaybackReggaeFeeling();
        } else {
            PauseReggaeFeeling();
        }
    }

    /**
     * Starts playback of second audio
     */
    private void PlaybackReggaeFeeling() {
        ConfigureSoundEffects(mpReggaeFeeling.getAudioSessionId());
        mpReggaeFeeling.start();
    }

    /**
     * Halts playback of second audio
     */
    private void PauseReggaeFeeling() {
        mpReggaeFeeling.pause();
    }

    /**
     * Configures the sound FX setup based on a given session
     * @param sessionID The session to apply FX on
     */
    private void ConfigureSoundEffects(int sessionID) {
        // Disable all prior FX
        if(bassBoost != null) {
            bassBoost.setEnabled(false);
        }
        if(virtualizer != null) {
            virtualizer.setEnabled(false);
        }

        // BASS BOOST
        bassBoost = new BassBoost(0, sessionID);
        if(bassBoost.getStrengthSupported()) {
            bassBoost.setStrength((short) 1000);
        }
        bassBoost.setEnabled(true);
        tbtnBassBoost.setChecked(true);

        // VIRTUALIZER
        virtualizer = new Virtualizer(0, sessionID);
        virtualizer.setStrength((short) 1000);
        virtualizer.setEnabled(true);
        tbtnVirtualizer.setChecked(true);
    }

    /**
     * Handle Bass Boost Switch
     */
    private void BassBoostClicked() {
        if(bassBoost == null) return;

        if(tbtnBassBoost.isChecked()) {
            bassBoost.setEnabled(true);
        } else {
            bassBoost.setEnabled(false);
        }
    }

    /**
     * Handle Virtualizer Switch
     */
    private void VirtualizerClicked() {
        if(virtualizer == null) return;

        if(tbtnVirtualizer.isChecked()) {
            virtualizer.setEnabled(true);
        } else {
            virtualizer.setEnabled(false);
        }
    }

}
