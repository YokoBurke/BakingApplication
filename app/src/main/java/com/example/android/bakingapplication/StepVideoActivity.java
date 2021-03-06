package com.example.android.bakingapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingapplication.data.steps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class StepVideoActivity extends AppCompatActivity implements ExoPlayer.EventListener  {

    private final String LOG_TAG = StepVideoActivity.class.getSimpleName();

    private TextView stepIdTextView;
    private TextView shortDescTextView;
    private TextView descTextView;
    private ImageView noVideoImageView;

    private Button previousButton;
    private Button nextButton;

    private SimpleExoPlayer mSimpleExoPlayer;
    private SimpleExoPlayerView mPlayerView;

    private MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;
    private NotificationManager mNotificationManager;

    steps mySteps;
    private ArrayList<steps> myStepsData;

    int currentStep;

    private boolean videoExists;
    String myRecipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_video);

        stepIdTextView = (TextView) findViewById(R.id.steps_id) ;
        shortDescTextView = (TextView) findViewById(R.id.steps_shortdescription);
        descTextView = (TextView) findViewById(R.id.steps_description);
        mPlayerView = (SimpleExoPlayerView) findViewById(R.id.playerView);
        noVideoImageView = (ImageView) findViewById(R.id.no_video);

        previousButton = (Button) findViewById(R.id.steps_previous);
        nextButton = (Button) findViewById(R.id.steps_next);

        Intent childIntent = getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)){
            mySteps = (steps) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);
            myStepsData = childIntent.getParcelableArrayListExtra("vlist");
            myRecipeName = childIntent.getStringExtra("recipe_name");
            currentStep = mySteps.getId();
            Log.i(LOG_TAG, "Current Step is:" + currentStep);
        }
        getSupportActionBar().setTitle(myRecipeName);
        Log.i(LOG_TAG, "Video URL: " + mySteps.getVideoURL());

        displayStepData();


        previousButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

               if (currentStep == 0){

                    Toast.makeText(getApplicationContext(), "This is the first step.", Toast.LENGTH_SHORT).show();

                } else {

                    currentStep--;
                    mySteps = myStepsData.get(currentStep);
                   Log.i(LOG_TAG, "Current Step after change:" + currentStep + mySteps.getShortDescription());
                    displayStepData();
                }

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.i(LOG_TAG, "Hope it can reach here!" + Integer.toString(myStepsData.size()));

                if (currentStep == myStepsData.size()-1){
                    Toast.makeText(getApplicationContext(), "This is the last step.", Toast.LENGTH_SHORT).show();
                }else {
                    currentStep++;
                    mySteps = myStepsData.get(currentStep);

                    displayStepData();
                }

            }
        });

        Log.i("StepVideoAct", mySteps.getVideoURL());

    }

    private void displayStepData(){

        Log.i(LOG_TAG, "display step data"+ mySteps.getShortDescription());

        if (mySteps.getVideoURL().isEmpty()) {
            //mSimpleExoPlayer.setPlayWhenReady(false);
            noVideoImageView.setVisibility(View.VISIBLE);
            mPlayerView.setVisibility(View.GONE);
            videoExists = false;
        } else {
            noVideoImageView.setVisibility(View.GONE);
            mPlayerView.setVisibility(View.VISIBLE);
            videoExists = true;

            initializeMediaSession();
            initializePlayer(Uri.parse(mySteps.getVideoURL()));
        }


        stepIdTextView.setText(Integer.toString(mySteps.getId()));
        shortDescTextView.setText(mySteps.getShortDescription());
        descTextView.setText(mySteps.getDescription());
    }

    private void initializePlayer(Uri mediaUri) {
        if (mSimpleExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
            mPlayerView.setPlayer(mSimpleExoPlayer);

            mSimpleExoPlayer.addListener(this);


        }

        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(this, Util.getUserAgent(this, "BakingApplication")), new DefaultExtractorsFactory(), null, null);
        mSimpleExoPlayer.prepare(mediaSource);
        mSimpleExoPlayer.setPlayWhenReady(true);
    }

    private void initializeMediaSession(){
        mMediaSession = new MediaSessionCompat(this, LOG_TAG);

        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS  |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mMediaSession.setMediaButtonReceiver(null);

        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);
        mMediaSession.setPlaybackState(mStateBuilder.build());

        mMediaSession.setCallback(new MySessionCallback());

        mMediaSession.setActive(true);

    }

    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mSimpleExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mSimpleExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mSimpleExoPlayer.seekTo(0);
        }
    }

    private void showNotification(PlaybackStateCompat state){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "M_CH_ID");

        int icon;
        String play_pause;
        if(state.getState() == PlaybackStateCompat.STATE_PLAYING){
            icon = R.drawable.exo_controls_pause;
            play_pause = getString(R.string.pause);
        } else {
            icon = R.drawable.exo_controls_play;
            play_pause = getString(R.string.play);
        }

        NotificationCompat.Action playPauseAction = new NotificationCompat.Action(
                icon, play_pause, MediaButtonReceiver.buildMediaButtonPendingIntent(this, PlaybackStateCompat.ACTION_PLAY_PAUSE)
        );

        PendingIntent contentPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, StepVideoActivity.class), 0);

        builder.setContentTitle(getString(R.string.app_name))
                .setContentIntent(contentPendingIntent)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_music_note)
                .addAction(playPauseAction)
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mMediaSession.getSessionToken())
                        .setShowActionsInCompactView(0));


        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, builder.build());


    }

    @Override
    protected void onPause(){
        super.onPause();
        if (videoExists) {
            mSimpleExoPlayer.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        if (videoExists) {
            mSimpleExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {

        if (videoExists) {
        mSimpleExoPlayer.stop();
        mSimpleExoPlayer.release();
        mSimpleExoPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoExists) {
            releasePlayer();
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        Log.i(LOG_TAG, "onPlayerSTateChanged Launched" + mSimpleExoPlayer.getPlaybackState());
        if((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
            Log.d(LOG_TAG, "onPlayerStateChanged: PLAYING");
        } else if((playbackState == ExoPlayer.STATE_READY)){
            Log.d(LOG_TAG, "onPlayerStateChanged: PAUSED");
        }

        mMediaSession.setPlaybackState(mStateBuilder.build());
        showNotification(mStateBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }
}
