package jp.chikaharu11.instant_drumpad_tr808

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.*
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.hypot


class MainActivity : AppCompatActivity(), CustomAdapterListener {

    private lateinit var adViewContainer: FrameLayout
    private lateinit var admobmAdView: AdView

    private val handler = Handler()
    private var mpDuration = 4189
    private var mpDuration2 = 625
    private var mpDuration3 = 4189
    private var mpDuration4 = 1033
    private var mpDuration5 = 1465
    private var mpDuration6 = 4418
    private var mpDuration7 = 794
    private var mpDuration8 = 1065
    private var mpDuration9 = 1065
    private var mpDuration10 = 1137
    private var mpDuration11 = 773
    private var mpDuration12 = 1070
    private var mpDuration13 = 2824
    private var mpDuration14 = 2526
    private var mpDuration15 = 1935

    companion object {
        private const val READ_REQUEST_CODE2: Int = 43
        private const val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 41
        private const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 42
    }

    @SuppressLint("Range")
    fun selectEX() {
        if (!isReadExternalStoragePermissionGranted()) {
            requestReadExternalStoragePermission()
        } else {
            tSoundList.clear()
            val audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val cursor = contentResolver.query(audioUri, null, null, null, null)
            cursor!!.moveToFirst()
            val path: Array<String?> = arrayOfNulls(cursor.count)
            for (i in path.indices) {
                path[i] = cursor.getString(cursor.getColumnIndex("_data"))
                tSoundList.add(SoundList(path[i].toString()))
                cursor.moveToNext()
            }

            cursor.close()
        }
    }

    private lateinit var soundPool: SoundPool

    private lateinit var mp: MediaPlayer

    private lateinit var getmpDuration: MediaPlayer

    private lateinit var lmp: LoopMediaPlayer

    private lateinit var aCustomAdapter: CustomAdapter
    private lateinit var bCustomAdapter: CustomAdapter
    private lateinit var cCustomAdapter: CustomAdapter
    private lateinit var dCustomAdapter: CustomAdapter
    private lateinit var eCustomAdapter: CustomAdapter
    private lateinit var fCustomAdapter: CustomAdapter
    private lateinit var gCustomAdapter: CustomAdapter
    private lateinit var hCustomAdapter: CustomAdapter
    private lateinit var iCustomAdapter: CustomAdapter
    private lateinit var jCustomAdapter: CustomAdapter
    private lateinit var kCustomAdapter: CustomAdapter
    private lateinit var lCustomAdapter: CustomAdapter

    private lateinit var nCustomAdapter: CustomAdapter
    private lateinit var oCustomAdapter: CustomAdapter
    private lateinit var pCustomAdapter: CustomAdapter
    private lateinit var qCustomAdapter: CustomAdapter
    private lateinit var rCustomAdapter: CustomAdapter

    private lateinit var sCustomAdapter: CustomAdapter
    private lateinit var tCustomAdapter: CustomAdapter

    private lateinit var aSoundList: MutableList<SoundList>
    private lateinit var bSoundList: MutableList<SoundList>
    private lateinit var cSoundList: MutableList<SoundList>
    private lateinit var dSoundList: MutableList<SoundList>
    private lateinit var eSoundList: MutableList<SoundList>
    private lateinit var fSoundList: MutableList<SoundList>
    private lateinit var gSoundList: MutableList<SoundList>
    private lateinit var hSoundList: MutableList<SoundList>
    private lateinit var iSoundList: MutableList<SoundList>
    private lateinit var jSoundList: MutableList<SoundList>
    private lateinit var kSoundList: MutableList<SoundList>
    private lateinit var lSoundList: MutableList<SoundList>

    private lateinit var nSoundList: MutableList<SoundList>
    private lateinit var oSoundList: MutableList<SoundList>
    private lateinit var pSoundList: MutableList<SoundList>
    private lateinit var qSoundList: MutableList<SoundList>
    private lateinit var rSoundList: MutableList<SoundList>

    private lateinit var sSoundList: MutableList<SoundList>
    private lateinit var tSoundList: MutableList<SoundList>

    private var sound1 = 0
    private var sound2 = 0
    private var sound3 = 0
    private var sound4 = 0
    private var sound5 = 0
    private var sound6 = 0
    private var sound7 = 0
    private var sound8 = 0
    private var sound9 = 0
    private var sound10 = 0
    private var sound11 = 0
    private var sound12 = 0
    private var sound13 = 0
    private var sound14 = 0
    private var sound15 = 0
    private var sound16 = 0

    private var paste = 0

    private var buttonA = 0
    private var buttonB = 0


    @SuppressLint("ClickableViewAccessibility", "SetTextI18n", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdMob()
        loadAdMob()

        val orientation = resources.configuration.orientation
        when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                textView.text = "cymbal_01".replace("_"," ")
                textView2.text = "cowbell_01b".replace("_"," ")
                textView3.text = "cymbal_02".replace("_"," ")
                textView4.text = "open_hi_hat_01".replace("_"," ")
                textView5.text = "clap_01".replace("_"," ")
                textView6.text = "cymbal_10".replace("_"," ")
                textView7.text = "closed_hi_hat_01".replace("_"," ")
                textView8.text = "high_tom_01".replace("_"," ")
                textView9.text = "mid_tom_01".replace("_"," ")
                textView10.text = "snare_drum_01".replace("_"," ")
                textView11.text = "bass_drum_short_01".replace("_"," ")
                textView12.text = "low_tom_01".replace("_"," ")
                textView13.text = "loop_sd_85_01".replace("_"," ")
                textView14.text = "loop_toms_95_02".replace("_"," ")
                textView15.text = "loop_bd_124_07".replace("_"," ")
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                textView.text = "cymbal_01"
                textView2.text = "cowbell_01b"
                textView3.text = "cymbal_02"
                textView4.text = "open_hi_hat_01"
                textView5.text = "clap_01"
                textView6.text = "cymbal_10"
                textView7.text = "closed_hi_hat_01"
                textView8.text = "high_tom_01"
                textView9.text = "mid_tom_01"
                textView10.text = "snare_drum_01"
                textView11.text = "bass_drum_short_01"
                textView12.text = "low_tom_01"
                textView13.text = "loop_sd_85_01"
                textView14.text = "loop_toms_95_02"
                textView15.text = "loop_bd_124_07"
            }
            Configuration.ORIENTATION_SQUARE -> {
                TODO()
            }
            Configuration.ORIENTATION_UNDEFINED -> {
                TODO()
            }
        }


        aSoundList = arrayListOf(
                SoundList("bass_drum_long_01.ogg"),
                SoundList("bass_drum_long_02.ogg"),
                SoundList("bass_drum_long_03.ogg"),
                SoundList("bass_drum_long_04.ogg"),
                SoundList("bass_drum_long_05.ogg"),
                SoundList("bass_drum_long_06.ogg"),
                SoundList("bass_drum_long_07.ogg"),
                SoundList("bass_drum_long_08.ogg"),
                SoundList("bass_drum_long_09.ogg"),
                SoundList("bass_drum_long_10.ogg"),
                SoundList("bass_drum_long_11.ogg"),
                SoundList("bass_drum_long_12.ogg"),
                SoundList("bass_drum_long_13.ogg"),
                SoundList("bass_drum_long_14.ogg"),
                SoundList("bass_drum_long_15.ogg"),
                SoundList("bass_drum_long_16.ogg"),
                SoundList("bass_drum_long_17.ogg"),
                SoundList("bass_drum_short_01.ogg"),
                SoundList("bass_drum_short_02.ogg"),
                SoundList("bass_drum_short_03.ogg"),
                SoundList("bass_drum_short_04.ogg"),
                SoundList("bass_drum_short_05.ogg"),
                SoundList("bass_drum_short_06.ogg"),
                SoundList("bass_drum_short_07.ogg"),
                SoundList("bass_drum_short_08.ogg"),
                SoundList("bass_drum_short_09.ogg"),
                SoundList("bass_drum_short_10.ogg"),
                SoundList("bass_drum_short_11.ogg"),
                SoundList("bass_drum_short_12.ogg")
                )

        bSoundList = arrayListOf(
                SoundList("clap_01.ogg"),
                SoundList("clap_02.ogg"),
                SoundList("clap_03.ogg"),
                SoundList("clap_04.ogg"),
                SoundList("clap_05.ogg"),
                SoundList("clap_06.ogg"),
                SoundList("clap_07.ogg"),
                SoundList("clap_08.ogg"),
                SoundList("clap_09.ogg"),
                SoundList("clap_10.ogg"),
                SoundList("clap_11.ogg"),
                SoundList("clap_12.ogg"),
                SoundList("clap_13.ogg"),
                SoundList("clap_14.ogg"),
                SoundList("clap_15.ogg")
        )
        cSoundList = arrayListOf(
                SoundList("claves_01.ogg"),
                SoundList("claves_02.ogg"),
                SoundList("claves_03.ogg"),
                SoundList("claves_04.ogg"),
                SoundList("claves_05.ogg"),
                SoundList("claves_06.ogg"),
                SoundList("claves_07.ogg"),
                SoundList("claves_08.ogg"),
                SoundList("claves_09.ogg"),
                SoundList("claves_10.ogg"),
                SoundList("claves_11.ogg")
        )
        dSoundList = arrayListOf(
                SoundList("closed_hi_hat_01.ogg"),
                SoundList("closed_hi_hat_02.ogg"),
                SoundList("closed_hi_hat_03.ogg"),
                SoundList("closed_hi_hat_04.ogg"),
                SoundList("closed_hi_hat_05.ogg"),
                SoundList("closed_hi_hat_06.ogg"),
                SoundList("closed_hi_hat_07.ogg"),
                SoundList("closed_hi_hat_08.ogg"),
                SoundList("closed_hi_hat_09.ogg"),
                SoundList("closed_hi_hat_10.ogg"),
                SoundList("closed_hi_hat_11.ogg"),
                SoundList("closed_hi_hat_12.ogg")
        )
        eSoundList = arrayListOf(
                SoundList("high_conga_01.ogg"),
                SoundList("high_conga_02.ogg"),
                SoundList("high_conga_03.ogg"),
                SoundList("high_conga_04.ogg"),
                SoundList("high_conga_05.ogg"),
                SoundList("high_conga_06.ogg"),
                SoundList("high_conga_07.ogg"),
                SoundList("high_conga_08.ogg"),
                SoundList("high_conga_09.ogg"),
                SoundList("high_conga_10.ogg"),
                SoundList("mid_conga_01.ogg"),
                SoundList("mid_conga_02.ogg"),
                SoundList("mid_conga_03.ogg"),
                SoundList("mid_conga_04.ogg"),
                SoundList("mid_conga_05.ogg"),
                SoundList("mid_conga_06.ogg"),
                SoundList("mid_conga_07.ogg"),
                SoundList("mid_conga_08.ogg"),
                SoundList("mid_conga_09.ogg"),
                SoundList("mid_conga_10.ogg"),
                SoundList("low_conga_01.ogg"),
                SoundList("low_conga_02.ogg"),
                SoundList("low_conga_03.ogg"),
                SoundList("low_conga_04.ogg"),
                SoundList("low_conga_05.ogg"),
                SoundList("low_conga_06.ogg"),
                SoundList("low_conga_07.ogg"),
                SoundList("low_conga_08.ogg"),
                SoundList("low_conga_09.ogg"),
                SoundList("low_conga_10.ogg")
        )
        fSoundList = arrayListOf(
                SoundList("cowbell_01a.ogg"),
                SoundList("cowbell_01b.ogg"),
                SoundList("cowbell_02.ogg"),
                SoundList("cowbell_03.ogg"),
                SoundList("cowbell_04.ogg"),
                SoundList("cowbell_05.ogg"),
                SoundList("cowbell_06.ogg"),
                SoundList("cowbell_07.ogg"),
                SoundList("cowbell_08.ogg"),
                SoundList("cowbell_09.ogg"),
                SoundList("cowbell_10.ogg"),
                SoundList("cowbell_11.ogg"),
                SoundList("cowbell_12.ogg"),
                SoundList("cowbell_13.ogg"),
                SoundList("cowbell_14.ogg"),
                SoundList("cowbell_15.ogg")
        )
        gSoundList = arrayListOf(
                SoundList("cymbal_01.ogg"),
                SoundList("cymbal_02.ogg"),
                SoundList("cymbal_03.ogg"),
                SoundList("cymbal_04.ogg"),
                SoundList("cymbal_05.ogg"),
                SoundList("cymbal_06.ogg"),
                SoundList("cymbal_07.ogg"),
                SoundList("cymbal_08.ogg"),
                SoundList("cymbal_09.ogg"),
                SoundList("cymbal_10.ogg"),
                SoundList("cymbal_11.ogg"),
                SoundList("cymbal_12.ogg"),
                SoundList("cymbal_13.ogg"),
                SoundList("cymbal_14.ogg"),
                SoundList("cymbal_15.ogg"),
                SoundList("cymbal_16.ogg")
        )
        hSoundList = arrayListOf(
                SoundList("maracas_01.ogg"),
                SoundList("maracas_02.ogg"),
                SoundList("maracas_03.ogg"),
                SoundList("maracas_04.ogg"),
                SoundList("maracas_05.ogg"),
                SoundList("maracas_06.ogg"),
                SoundList("maracas_07.ogg"),
                SoundList("maracas_08.ogg"),
                SoundList("maracas_09.ogg"),
                SoundList("maracas_10.ogg"),
                SoundList("maracas_11.ogg")
        )
        iSoundList = arrayListOf(
                SoundList("open_hi_hat_01.ogg"),
                SoundList("open_hi_hat_02.ogg"),
                SoundList("open_hi_hat_03.ogg"),
                SoundList("open_hi_hat_04.ogg"),
                SoundList("open_hi_hat_05.ogg"),
                SoundList("open_hi_hat_06.ogg"),
                SoundList("open_hi_hat_07.ogg"),
                SoundList("open_hi_hat_08.ogg"),
                SoundList("open_hi_hat_09.ogg"),
                SoundList("open_hi_hat_10.ogg"),
                SoundList("open_hi_hat_11.ogg"),
                SoundList("open_hi_hat_12.ogg"),
                SoundList("open_hi_hat_13.ogg")
        )
        jSoundList = arrayListOf(
                SoundList("rimshot_01.ogg"),
                SoundList("rimshot_02.ogg"),
                SoundList("rimshot_03.ogg"),
                SoundList("rimshot_04.ogg"),
                SoundList("rimshot_05.ogg"),
                SoundList("rimshot_06.ogg"),
                SoundList("rimshot_07.ogg"),
                SoundList("rimshot_08.ogg"),
                SoundList("rimshot_09.ogg"),
                SoundList("rimshot_10.ogg"),
                SoundList("rimshot_11.ogg")
        )
        kSoundList = arrayListOf(
                SoundList("snare_drum_01.ogg"),
                SoundList("snare_drum_02.ogg"),
                SoundList("snare_drum_03.ogg"),
                SoundList("snare_drum_04.ogg"),
                SoundList("snare_drum_05.ogg"),
                SoundList("snare_drum_06.ogg"),
                SoundList("snare_drum_07.ogg"),
                SoundList("snare_drum_08.ogg"),
                SoundList("snare_drum_09.ogg"),
                SoundList("snare_drum_10.ogg"),
                SoundList("snare_drum_11.ogg"),
                SoundList("snare_drum_12.ogg"),
                SoundList("snare_drum_13.ogg"),
                SoundList("snare_drum_14.ogg"),
                SoundList("snare_drum_15.ogg"),
                SoundList("snare_drum_16.ogg"),
                SoundList("snare_drum_17.ogg"),
                SoundList("snare_drum_18.ogg"),
                SoundList("snare_drum_19.ogg"),
                SoundList("snare_drum_20.ogg")
        )
        lSoundList = arrayListOf(
                SoundList("low_tom_01.ogg"),
                SoundList("low_tom_02.ogg"),
                SoundList("low_tom_03.ogg"),
                SoundList("low_tom_04.ogg"),
                SoundList("low_tom_05.ogg"),
                SoundList("low_tom_06.ogg"),
                SoundList("low_tom_07.ogg"),
                SoundList("low_tom_08.ogg"),
                SoundList("low_tom_09.ogg"),
                SoundList("low_tom_10.ogg"),
                SoundList("low_tom_11.ogg"),
                SoundList("low_tom_12.ogg"),
                SoundList("mid_tom_01.ogg"),
                SoundList("mid_tom_02.ogg"),
                SoundList("mid_tom_03.ogg"),
                SoundList("mid_tom_04.ogg"),
                SoundList("mid_tom_05.ogg"),
                SoundList("mid_tom_06.ogg"),
                SoundList("mid_tom_07.ogg"),
                SoundList("mid_tom_08.ogg"),
                SoundList("mid_tom_09.ogg"),
                SoundList("mid_tom_10.ogg"),
                SoundList("mid_tom_11.ogg"),
                SoundList("mid_tom_12.ogg"),
                SoundList("high_tom_01.ogg"),
                SoundList("high_tom_02.ogg"),
                SoundList("high_tom_03.ogg"),
                SoundList("high_tom_04.ogg"),
                SoundList("high_tom_05.ogg"),
                SoundList("high_tom_06.ogg"),
                SoundList("high_tom_07.ogg"),
                SoundList("high_tom_08.ogg"),
                SoundList("high_tom_09.ogg"),
                SoundList("high_tom_10.ogg"),
                SoundList("high_tom_11.ogg"),
                SoundList("high_tom_12.ogg")
        )
        nSoundList = arrayListOf(
                SoundList("loop_bd_85_01.ogg"),
                SoundList("loop_bd_85_02.ogg"),
                SoundList("loop_bd_85_03.ogg"),
                SoundList("loop_bd_85_04.ogg"),
                SoundList("loop_bd_85_05.ogg"),
                SoundList("loop_bd_85_06.ogg"),
                SoundList("loop_bd_85_07.ogg"),
                SoundList("loop_bd_85_08.ogg"),
                SoundList("loop_hats_85_01.ogg"),
                SoundList("loop_hats_85_02.ogg"),
                SoundList("loop_hats_85_03.ogg"),
                SoundList("loop_hats_85_04.ogg"),
                SoundList("loop_hats_85_05.ogg"),
                SoundList("loop_hats_85_06.ogg"),
                SoundList("loop_hats_85_07.ogg"),
                SoundList("loop_hats_85_08.ogg"),
                SoundList("loop_perc_85_01.ogg"),
                SoundList("loop_perc_85_02.ogg"),
                SoundList("loop_perc_85_03.ogg"),
                SoundList("loop_perc_85_04.ogg"),
                SoundList("loop_sd_85_01.ogg"),
                SoundList("loop_sd_85_02.ogg"),
                SoundList("loop_sd_85_03.ogg"),
                SoundList("loop_sd_85_04.ogg"),
                SoundList("loop_sd_85_05.ogg"),
                SoundList("loop_sd_85_06.ogg"),
                SoundList("loop_sd_85_07.ogg"),
                SoundList("loop_sd_85_08.ogg"),
                SoundList("loop_toms_85_01.ogg"),
                SoundList("loop_toms_85_02.ogg"),
                SoundList("loop_toms_85_03.ogg"),
                SoundList("loop_toms_85_04.ogg")
        )
        oSoundList = arrayListOf(
                SoundList("loop_bd_95_01.ogg"),
                SoundList("loop_bd_95_02.ogg"),
                SoundList("loop_bd_95_03.ogg"),
                SoundList("loop_bd_95_04.ogg"),
                SoundList("loop_bd_95_05.ogg"),
                SoundList("loop_bd_95_06.ogg"),
                SoundList("loop_bd_95_07.ogg"),
                SoundList("loop_bd_95_08.ogg"),
                SoundList("loop_hats_95_01.ogg"),
                SoundList("loop_hats_95_02.ogg"),
                SoundList("loop_hats_95_03.ogg"),
                SoundList("loop_hats_95_04.ogg"),
                SoundList("loop_hats_95_05.ogg"),
                SoundList("loop_hats_95_06.ogg"),
                SoundList("loop_hats_95_07.ogg"),
                SoundList("loop_hats_95_08.ogg"),
                SoundList("loop_perc_95_01.ogg"),
                SoundList("loop_perc_95_02.ogg"),
                SoundList("loop_perc_95_03.ogg"),
                SoundList("loop_perc_95_04.ogg"),
                SoundList("loop_sd_95_01.ogg"),
                SoundList("loop_sd_95_02.ogg"),
                SoundList("loop_sd_95_03.ogg"),
                SoundList("loop_sd_95_04.ogg"),
                SoundList("loop_sd_95_05.ogg"),
                SoundList("loop_sd_95_06.ogg"),
                SoundList("loop_sd_95_07.ogg"),
                SoundList("loop_sd_95_08.ogg"),
                SoundList("loop_toms_95_01.ogg"),
                SoundList("loop_toms_95_02.ogg"),
                SoundList("loop_toms_95_03.ogg"),
                SoundList("loop_toms_95_04.ogg")
        )
        pSoundList = arrayListOf(
                SoundList("loop_bd_105_01.ogg"),
                SoundList("loop_bd_105_02.ogg"),
                SoundList("loop_bd_105_03.ogg"),
                SoundList("loop_bd_105_04.ogg"),
                SoundList("loop_bd_105_05.ogg"),
                SoundList("loop_bd_105_06.ogg"),
                SoundList("loop_bd_105_07.ogg"),
                SoundList("loop_bd_105_08.ogg"),
                SoundList("loop_hats_105_01.ogg"),
                SoundList("loop_hats_105_02.ogg"),
                SoundList("loop_hats_105_03.ogg"),
                SoundList("loop_hats_105_04.ogg"),
                SoundList("loop_hats_105_05.ogg"),
                SoundList("loop_hats_105_06.ogg"),
                SoundList("loop_hats_105_07.ogg"),
                SoundList("loop_hats_105_08.ogg"),
                SoundList("loop_perc_105_01.ogg"),
                SoundList("loop_perc_105_02.ogg"),
                SoundList("loop_perc_105_03.ogg"),
                SoundList("loop_perc_105_04.ogg"),
                SoundList("loop_sd_105_01.ogg"),
                SoundList("loop_sd_105_02.ogg"),
                SoundList("loop_sd_105_03.ogg"),
                SoundList("loop_sd_105_04.ogg"),
                SoundList("loop_sd_105_05.ogg"),
                SoundList("loop_sd_105_06.ogg"),
                SoundList("loop_sd_105_07.ogg"),
                SoundList("loop_sd_105_08.ogg"),
                SoundList("loop_toms_105_01.ogg"),
                SoundList("loop_toms_105_02.ogg"),
                SoundList("loop_toms_105_03.ogg"),
                SoundList("loop_toms_105_04.ogg")
        )
        qSoundList = arrayListOf(
                SoundList("loop_bd_124_01.ogg"),
                SoundList("loop_bd_124_02.ogg"),
                SoundList("loop_bd_124_03.ogg"),
                SoundList("loop_bd_124_04.ogg"),
                SoundList("loop_bd_124_05.ogg"),
                SoundList("loop_bd_124_06.ogg"),
                SoundList("loop_bd_124_07.ogg"),
                SoundList("loop_bd_124_08.ogg"),
                SoundList("loop_hats_124_01.ogg"),
                SoundList("loop_hats_124_02.ogg"),
                SoundList("loop_hats_124_03.ogg"),
                SoundList("loop_hats_124_04.ogg"),
                SoundList("loop_hats_124_05.ogg"),
                SoundList("loop_hats_124_06.ogg"),
                SoundList("loop_hats_124_07.ogg"),
                SoundList("loop_hats_124_08.ogg"),
                SoundList("loop_perc_124_01.ogg"),
                SoundList("loop_perc_124_02.ogg"),
                SoundList("loop_perc_124_03.ogg"),
                SoundList("loop_perc_124_04.ogg"),
                SoundList("loop_sd_124_01.ogg"),
                SoundList("loop_sd_124_02.ogg"),
                SoundList("loop_sd_124_03.ogg"),
                SoundList("loop_sd_124_04.ogg"),
                SoundList("loop_sd_124_05.ogg"),
                SoundList("loop_sd_124_06.ogg"),
                SoundList("loop_sd_124_07.ogg"),
                SoundList("loop_toms_124_01.ogg"),
                SoundList("loop_toms_124_02.ogg"),
                SoundList("loop_toms_124_03.ogg"),
                SoundList("loop_toms_124_04.ogg")
        )
        rSoundList = arrayListOf(
                SoundList("loop_bd_132_01.ogg"),
                SoundList("loop_bd_132_02.ogg"),
                SoundList("loop_bd_132_03.ogg"),
                SoundList("loop_bd_132_04.ogg"),
                SoundList("loop_bd_132_05.ogg"),
                SoundList("loop_bd_132_06.ogg"),
                SoundList("loop_bd_132_07.ogg"),
                SoundList("loop_bd_132_08.ogg"),
                SoundList("loop_hats_132_01.ogg"),
                SoundList("loop_hats_132_02.ogg"),
                SoundList("loop_hats_132_03.ogg"),
                SoundList("loop_hats_132_04.ogg"),
                SoundList("loop_hats_132_05.ogg"),
                SoundList("loop_hats_132_06.ogg"),
                SoundList("loop_hats_132_07.ogg"),
                SoundList("loop_hats_132_08.ogg"),
                SoundList("loop_perc_132_01.ogg"),
                SoundList("loop_perc_132_02.ogg"),
                SoundList("loop_perc_132_03.ogg"),
                SoundList("loop_perc_132_04.ogg"),
                SoundList("loop_sd_132_01.ogg"),
                SoundList("loop_sd_132_02.ogg"),
                SoundList("loop_sd_132_03.ogg"),
                SoundList("loop_sd_132_04.ogg"),
                SoundList("loop_sd_132_05.ogg"),
                SoundList("loop_sd_132_06.ogg"),
                SoundList("loop_sd_132_07.ogg"),
                SoundList("loop_sd_132_08.ogg"),
                SoundList("loop_toms_132_01.ogg"),
                SoundList("loop_toms_132_02.ogg"),
                SoundList("loop_toms_132_03.ogg"),
                SoundList("loop_toms_132_04.ogg")
        )
        sSoundList = arrayListOf()
        tSoundList = arrayListOf()

        val listView = findViewById<ListView>(R.id.list_view)

        aCustomAdapter = CustomAdapter(this, aSoundList, this)
        bCustomAdapter = CustomAdapter(this, bSoundList, this)
        cCustomAdapter = CustomAdapter(this, cSoundList, this)
        dCustomAdapter = CustomAdapter(this, dSoundList, this)
        eCustomAdapter = CustomAdapter(this, eSoundList, this)
        fCustomAdapter = CustomAdapter(this, fSoundList, this)
        gCustomAdapter = CustomAdapter(this, gSoundList, this)
        hCustomAdapter = CustomAdapter(this, hSoundList, this)
        iCustomAdapter = CustomAdapter(this, iSoundList, this)
        jCustomAdapter = CustomAdapter(this, jSoundList, this)
        kCustomAdapter = CustomAdapter(this, kSoundList, this)
        lCustomAdapter = CustomAdapter(this, lSoundList, this)
        nCustomAdapter = CustomAdapter(this, nSoundList, this)
        oCustomAdapter = CustomAdapter(this, oSoundList, this)
        pCustomAdapter = CustomAdapter(this, pSoundList, this)
        qCustomAdapter = CustomAdapter(this, qSoundList, this)
        rCustomAdapter = CustomAdapter(this, rSoundList, this)
        sCustomAdapter = CustomAdapter(this, sSoundList, this)
        tCustomAdapter = CustomAdapter(this, tSoundList, this)

        listView.adapter = aCustomAdapter

        mp = MediaPlayer()

        supportActionBar?.title ="loop_toms_85_01".replace("_"," ")


            val audioUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
            val cursor = contentResolver.query(audioUri, null, null, null, null)
            cursor!!.moveToFirst()
            val path: Array<String?> = arrayOfNulls(cursor.count)
            for (i in path.indices) {
                path[i] = cursor.getString(cursor.getColumnIndex("_data"))
                sSoundList.add(SoundList(path[i].toString()))
                cursor.moveToNext()
            }

            cursor.close()


        val meSpinner = findViewById<Spinner>(R.id.menu_spinner)

        val adapter3 = ArrayAdapter.createFromResource(this, R.array.spinnerItems, android.R.layout.simple_spinner_item)

        adapter3.setDropDownViewResource(R.layout.custom_spinner_dropdown)



        meSpinner.adapter = adapter3


        meSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!meSpinner.isFocusable) {
                    meSpinner.isFocusable = true
                    return
                }

                val soundListView = findViewById<ListView>(R.id.list_view)

                when(position){
                    0 -> {
                        buttonB = 2
                        soundListView.adapter = aCustomAdapter
                        aCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    1 -> {
                        buttonB = 2
                        soundListView.adapter = bCustomAdapter
                        bCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    2 -> {
                        buttonB = 2
                        soundListView.adapter = cCustomAdapter
                        cCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    3 -> {
                        buttonB = 2
                        soundListView.adapter = dCustomAdapter
                        dCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    4 -> {
                        buttonB = 2
                        soundListView.adapter = eCustomAdapter
                        eCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    5 -> {
                        buttonB = 2
                        soundListView.adapter = fCustomAdapter
                        fCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    6 -> {
                        buttonB = 2
                        soundListView.adapter = gCustomAdapter
                        gCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    7 -> {
                        buttonB = 2
                        soundListView.adapter = hCustomAdapter
                        hCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    8 -> {
                        buttonB = 2
                        soundListView.adapter = iCustomAdapter
                        iCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    9 -> {
                        buttonB = 2
                        soundListView.adapter = jCustomAdapter
                        jCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    10 -> {
                        buttonB = 2
                        soundListView.adapter = kCustomAdapter
                        kCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    11 -> {
                        buttonB = 2
                        soundListView.adapter = lCustomAdapter
                        lCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    12 -> {
                        buttonB = 2
                        soundListView.adapter = nCustomAdapter
                        nCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    13 -> {
                        buttonB = 2
                        soundListView.adapter = oCustomAdapter
                        oCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    14 -> {
                        buttonB = 2
                        soundListView.adapter = pCustomAdapter
                        pCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    15 -> {
                        buttonB = 2
                        soundListView.adapter = qCustomAdapter
                        qCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    16 -> {
                        buttonB = 2
                        soundListView.adapter = rCustomAdapter
                        rCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    17 -> {
                        buttonB = 1
                        soundListView.adapter = sCustomAdapter
                        sCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    18 -> {
                        selectEX()
                        buttonB = 1
                        soundListView.adapter = tCustomAdapter
                        tCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        meSpinner.isFocusable = false


        val audioAttributes = AudioAttributes.Builder()

                .setUsage(AudioAttributes.USAGE_GAME)

                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()

        soundPool = SoundPool.Builder()

                .setAudioAttributes(audioAttributes)

                .setMaxStreams(20)
                .build()

        when (orientation) {

            Configuration.ORIENTATION_PORTRAIT -> {

                sound1 = soundPool.load(assets.openFd("cymbal_01.ogg"), 1)

                sound2 = soundPool.load(assets.openFd("cowbell_01b.ogg"), 1)

                sound3 = soundPool.load(assets.openFd("cymbal_02.ogg"), 1)

                sound4 = soundPool.load(assets.openFd("open_hi_hat_01.ogg"), 1)

                sound5 = soundPool.load(assets.openFd("clap_01.ogg"), 1)

                sound6 = soundPool.load(assets.openFd("cymbal_10.ogg"), 1)

                sound7 = soundPool.load(assets.openFd("closed_hi_hat_01.ogg"), 1)

                sound8 = soundPool.load(assets.openFd("high_tom_01.ogg"), 1)

                sound9 = soundPool.load(assets.openFd("mid_tom_01.ogg"), 1)

                sound10 = soundPool.load(assets.openFd("snare_drum_01.ogg"), 1)

                sound11 = soundPool.load(assets.openFd("bass_drum_short_01.ogg"), 1)

                sound12 = soundPool.load(assets.openFd("low_tom_01.ogg"), 1)

                sound13 = soundPool.load(assets.openFd("loop_sd_85_01.ogg"), 1)

                sound14 = soundPool.load(assets.openFd("loop_toms_95_02.ogg"), 1)

                sound15 = soundPool.load(assets.openFd("loop_bd_124_07.ogg"), 1)

            }

            Configuration.ORIENTATION_LANDSCAPE -> {

                sound1 = soundPool.load(assets.openFd("cymbal_01.ogg"), 1)

                sound2 = soundPool.load(assets.openFd("cowbell_01b.ogg"), 1)

                sound3 = soundPool.load(assets.openFd("cymbal_02.ogg"), 1)

                sound4 = soundPool.load(assets.openFd("open_hi_hat_01.ogg"), 1)

                sound5 = soundPool.load(assets.openFd("clap_01.ogg"), 1)

                sound6 = soundPool.load(assets.openFd("cymbal_10.ogg"), 1)

                sound7 = soundPool.load(assets.openFd("closed_hi_hat_01.ogg"), 1)

                sound8 = soundPool.load(assets.openFd("high_tom_01.ogg"), 1)

                sound9 = soundPool.load(assets.openFd("mid_tom_01.ogg"), 1)

                sound10 = soundPool.load(assets.openFd("snare_drum_01.ogg"), 1)

                sound11 = soundPool.load(assets.openFd("bass_drum_short_01.ogg"), 1)

                sound12 = soundPool.load(assets.openFd("low_tom_01.ogg"), 1)

                sound13 = soundPool.load(assets.openFd("loop_sd_85_01.ogg"), 1)

                sound14 = soundPool.load(assets.openFd("loop_toms_95_02.ogg"), 1)

                sound15 = soundPool.load(assets.openFd("loop_bd_124_07.ogg"), 1)

            }
            Configuration.ORIENTATION_SQUARE -> {
                TODO()
            }
            Configuration.ORIENTATION_UNDEFINED -> {
                TODO()
            }
        }

        lmp = LoopMediaPlayer.create(this, Uri.parse("android.resource://" + packageName + "/raw/" + R.raw.loop_toms_85_01))


        imageView.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound1, 1.0f, 1.0f, 1, 0, 1.0f)
                    effect(imageView,mpDuration)
                }
            }
                false
        }

        imageView2.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound2, 1.0f, 1.0f, 1, 0, 1.0f)
                    effect(imageView2,mpDuration2)
                }
            }
                false
        }

        imageView3.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound3, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView3,mpDuration3)
                }
            }
                false
        }

        imageView4.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound4, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView4,mpDuration4)
                }
            }
                false
        }

        imageView5.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound5, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView5,mpDuration5)
                }
            }
                false
        }

        imageView6.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound6, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView6,mpDuration6)
                }
            }
                false
        }

        imageView7.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound7, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView7,mpDuration7)
                }
            }
                false
        }

        imageView8.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound8, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView8,mpDuration8)
                }
            }
                false
        }

        imageView9.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound9, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView9,mpDuration9)
                }
            }
                false

        }

        imageView10.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound10, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView10,mpDuration10)
                }
            }
                false
        }

        imageView11.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound11, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView11,mpDuration11)
                }
            }
                false
        }

        imageView12.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound12, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView12,mpDuration12)
                }
            }
                false
        }

        imageView13.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound13, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView13,mpDuration13)
                }
            }
                false
        }

        imageView14.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound14, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView14,mpDuration14)
                }
            }
                false
        }

        imageView15.setOnTouchListener { _, event ->
            when {
                listView.isVisible -> {
                    listView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound15, 1.0f, 1.0f, 0, 0, 1.0f)
                    effect(imageView15,mpDuration15)
                }
            }
                false
        }


        imageView.setOnClickListener {
            if (paste == 1) {
                buttonA = 1
                meSpinner.performClick()
            }
        }
        imageView2.setOnClickListener {
            if (paste == 1) {
                buttonA = 2
                meSpinner.performClick()
            }
        }
        imageView3.setOnClickListener {
            if (paste == 1) {
                buttonA = 3
                meSpinner.performClick()
            }
        }
        imageView4.setOnClickListener {
            if (paste == 1) {
                buttonA = 4
                meSpinner.performClick()
            }
        }
        imageView5.setOnClickListener {
            if (paste == 1) {
                buttonA = 5
                meSpinner.performClick()
            }
        }
        imageView6.setOnClickListener {
            if (paste == 1) {
                buttonA = 6
                meSpinner.performClick()
            }
        }
        imageView7.setOnClickListener {
            if (paste == 1) {
                buttonA = 7
                meSpinner.performClick()
            }
        }
        imageView8.setOnClickListener {
            if (paste == 1) {
                buttonA = 8
                meSpinner.performClick()
            }
        }
        imageView9.setOnClickListener {
            if (paste == 1) {
                buttonA = 9
                meSpinner.performClick()
            }
        }
        imageView10.setOnClickListener {
            if (paste == 1) {
                buttonA = 10
                meSpinner.performClick()
            }
        }
        imageView11.setOnClickListener {
            if (paste == 1) {
                buttonA = 11
                meSpinner.performClick()
            }
        }
        imageView12.setOnClickListener {
            if (paste == 1) {
                buttonA = 12
                meSpinner.performClick()
            }
        }
        imageView13.setOnClickListener {
            if (paste == 1) {
                buttonA = 13
                meSpinner.performClick()
            }
        }
        imageView14.setOnClickListener {
            if (paste == 1) {
                buttonA = 14
                meSpinner.performClick()
            }
        }
        imageView15.setOnClickListener {
            if (paste == 1) {
                buttonA = 15
                meSpinner.performClick()
            }
        }
    }

    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density
            var adWidthPixels = adViewContainer.width.toFloat()
            if (adWidthPixels == 0.0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }
            val adWidth = (adWidthPixels / density).toInt()


            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this@MainActivity, adWidth)
        }

    private fun initAdMob() {
        adViewContainer = findViewById(R.id.adView)

        MobileAds.initialize(this) {}
        admobmAdView = AdView(this)
        admobmAdView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        admobmAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                adViewContainer.addView(admobmAdView)
            }
        }
    }

    private fun loadAdMob() {
        val request = AdRequest.Builder().build()
        admobmAdView.adSize = adSize
        admobmAdView.loadAd(request)
    }

    private fun effect(imageView: ImageView, mpDuration: Int) {
        val cx = imageView.width / 2
        val cy = imageView.height / 2

        val initialRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        val anim = ViewAnimationUtils.createCircularReveal(imageView, cx, cy, initialRadius, 0f)

        anim.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                imageView.setColorFilter(Color.parseColor("#e2e3e3"), PorterDuff.Mode.SRC_IN)
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                imageView.setColorFilter(Color.parseColor("#e2e3e3"), PorterDuff.Mode.SRC_IN)
            }
        })

        anim.duration = mpDuration.toLong()
        anim.start()
    }

    override fun clicked(soundList: SoundList) {
        sound16 = if (buttonB == 1) {
            soundPool.load(soundList.name, 1)
        } else {
            soundPool.load(assets.openFd(soundList.name), 1)
        }
            soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f)
            }
    }

    override fun clicked2(soundList: SoundList) {
        try {
            when {
                buttonA == 1 && buttonB == 1 -> {
                    imageView.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound1 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 2 && buttonB == 1 -> {
                    imageView2.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView2.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound2 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration2 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView2.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 3 && buttonB == 1 -> {
                    imageView3.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView3.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound3 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration3 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView3.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 4 && buttonB == 1 -> {
                    imageView4.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView4.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound4 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration4 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView4.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 5 && buttonB == 1 -> {
                    imageView5.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView5.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound5 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration5 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView5.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 6 && buttonB == 1 -> {
                    imageView6.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView6.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound6 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration6 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView6.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 7 && buttonB == 1 -> {
                    imageView7.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView7.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound7 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration7 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView7.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 8 && buttonB == 1 -> {
                    imageView8.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView8.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound8 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration8 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView8.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 9 && buttonB == 1 -> {
                    imageView9.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView9.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound9 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration9 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView9.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 10 && buttonB == 1 -> {
                    imageView10.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView10.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound10 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration10 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView10.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 11 && buttonB == 1 -> {
                    imageView11.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView11.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound11 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration11 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView11.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 12 && buttonB == 1 -> {
                    imageView12.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView12.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound12 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration12 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView12.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 13 && buttonB == 1 -> {
                    imageView13.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView13.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound13 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration13 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView13.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 14 && buttonB == 1 -> {
                    imageView14.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView14.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound14 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration14 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView14.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 15 && buttonB == 1 -> {
                    imageView15.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView15.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound15 = soundPool.load(soundList.name, 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(this, Uri.parse(soundList.name))
                    getmpDuration.prepare()
                    mpDuration15 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView15.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 16 && buttonB == 1 -> {
                    lmp.release()
                    lmp = LoopMediaPlayer(this@MainActivity, Uri.parse(soundList.name))
                    supportActionBar?.title = soundList.name.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                    soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                }
                buttonA == 1 && buttonB == 2 -> {
                    imageView.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound1 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 2 && buttonB == 2 -> {
                    imageView2.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView2.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound2 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration2 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView2.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 3 && buttonB == 2 -> {
                    imageView3.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView3.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound3 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration3 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView3.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 4 && buttonB == 2 -> {
                    imageView4.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView4.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound4 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration4 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView4.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 5 && buttonB == 2 -> {
                    imageView5.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView5.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound5 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration5 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView5.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 6 && buttonB == 2 -> {
                    imageView6.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView6.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound6 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration6 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView6.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 7 && buttonB == 2 -> {
                    imageView7.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView7.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound7 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration7 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView7.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 8 && buttonB == 2 -> {
                    imageView8.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView8.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound8 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration8 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView8.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 9 && buttonB == 2 -> {
                    imageView9.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView9.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound9 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration9 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView9.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 10 && buttonB == 2 -> {
                    imageView10.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView10.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound10 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration10 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView10.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 11 && buttonB == 2 -> {
                    imageView11.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView11.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound11 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration11 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView11.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 12 && buttonB == 2 -> {
                    imageView12.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView12.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound12 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration12 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView12.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 13 && buttonB == 2 -> {
                    imageView13.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView13.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound13 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration13 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView13.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 14 && buttonB == 2 -> {
                    imageView14.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView14.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound14 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration14 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView14.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 15 && buttonB == 2 -> {
                    imageView15.setColorFilter(Color.parseColor("#6B6A71"))
                    handler.postDelayed({ imageView15.setColorFilter(Color.parseColor("#e2e3e3")) },
                        1000)
                    sound15 = soundPool.load(assets.openFd(soundList.name), 1)
                    getmpDuration = MediaPlayer()
                    getmpDuration.setDataSource(assets.openFd(soundList.name).fileDescriptor,
                        assets.openFd(soundList.name).startOffset,
                        assets.openFd(soundList.name).declaredLength)
                    getmpDuration.prepare()
                    mpDuration15 = getmpDuration.duration
                    getmpDuration.release()
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    textView15.text = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                }
                buttonA == 16 -> {
                    lmp.release()
                    lmp = LoopMediaPlayer(this@MainActivity, Uri.parse("android.resource://" + packageName + "/raw/" + soundList.name.replace(".ogg", "")))
                    supportActionBar?.title = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                    soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                }
            }
        } catch (e: Exception) {
            Toast.makeText(applicationContext, R.string.error, Toast.LENGTH_LONG).show()
        }
        findViewById<ListView>(R.id.list_view).visibility = View.INVISIBLE
    }

    private fun isReadExternalStoragePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestReadExternalStoragePermission() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                RECORD_AUDIO_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_PERMISSION_REQUEST_CODE) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult1,
                        Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult2,
                        Toast.LENGTH_LONG
                ).show()
            }
        }

        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult1,
                        Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult2,
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {

            READ_REQUEST_CODE2 -> {

                resultData?.data?.also { uri ->
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":".toRegex()).toTypedArray()
                    val type = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        val item = "/storage/emulated/0/" + split[1]
                        when (buttonA) {
                            1 -> {
                                sound1 = soundPool.load(item, 1)
                                textView.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            2 -> {
                                sound2 = soundPool.load(item, 1)
                                textView2.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            3 -> {
                                sound3 = soundPool.load(item, 1)
                                textView3.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            4 -> {
                                sound4 = soundPool.load(item, 1)
                                textView4.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            5 -> {
                                sound5 = soundPool.load(item, 1)
                                textView5.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            6 -> {
                                sound6 = soundPool.load(item, 1)
                                textView6.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            7 -> {
                                sound7 = soundPool.load(item, 1)
                                textView7.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            8 -> {
                                sound8 = soundPool.load(item, 1)
                                textView8.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            9 -> {
                                sound9 = soundPool.load(item, 1)
                                textView9.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            10 -> {
                                sound10 = soundPool.load(item, 1)
                                textView10.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            11 -> {
                                sound11 = soundPool.load(item, 1)
                                textView11.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            12 -> {
                                sound12 = soundPool.load(item, 1)
                                textView12.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            13 -> {
                                sound13 = soundPool.load(item, 1)
                                textView13.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            14 -> {
                                sound14 = soundPool.load(item, 1)
                                textView14.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            15 -> {
                                sound15 = soundPool.load(item, 1)
                                textView15.text = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                            16 -> {
                                lmp.release()
                                lmp = LoopMediaPlayer(this@MainActivity, Uri.parse(item))
                                supportActionBar?.title = item.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                            }
                        }
                    } else {
                        try {
                            val item2 = "/stroage/" + type + "/" + split[1]
                            when (buttonA) {
                                1 -> {
                                    sound1 = soundPool.load(item2, 1)
                                    textView.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                2 -> {
                                    sound2 = soundPool.load(item2, 1)
                                    textView2.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                3 -> {
                                    sound3 = soundPool.load(item2, 1)
                                    textView3.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                4 -> {
                                    sound4 = soundPool.load(item2, 1)
                                    textView4.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                5 -> {
                                    sound5 = soundPool.load(item2, 1)
                                    textView5.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                6 -> {
                                    sound6 = soundPool.load(item2, 1)
                                    textView6.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                7 -> {
                                    sound7 = soundPool.load(item2, 1)
                                    textView7.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                8 -> {
                                    sound8 = soundPool.load(item2, 1)
                                    textView8.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                9 -> {
                                    sound9 = soundPool.load(item2, 1)
                                    textView9.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                10 -> {
                                    sound10 = soundPool.load(item2, 1)
                                    textView10.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                11 -> {
                                    sound11 = soundPool.load(item2, 1)
                                    textView11.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                12 -> {
                                    sound12 = soundPool.load(item2, 1)
                                    textView12.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                13 -> {
                                    sound13 = soundPool.load(item2, 1)
                                    textView13.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                14 -> {
                                    sound14 = soundPool.load(item2, 1)
                                    textView14.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                15 -> {
                                    sound15 = soundPool.load(item2, 1)
                                    textView15.text = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                                16 -> {
                                    lmp.release()
                                    lmp = LoopMediaPlayer(this@MainActivity, Uri.parse(item2))
                                    supportActionBar?.title = item2.replaceBeforeLast("/", "").replace("/", "").replaceAfterLast(".", "").replace("_", " ").replace(".", "")
                                }
                            }
                        } catch (e: Exception) {
                            Toast.makeText(applicationContext, R.string.READ_REQUEST_CODE2, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun selectCh() {

        val chSpinner = findViewById<Spinner>(R.id.choose_loop_spinner)

        val adapterC = ArrayAdapter.createFromResource(this, R.array.spinnerItems2, android.R.layout.simple_spinner_item)

        adapterC.setDropDownViewResource(R.layout.custom_spinner_dropdown)


        chSpinner.adapter = adapterC

        chSpinner.performClick()


        chSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!chSpinner.isFocusable) {
                    chSpinner.isFocusable = true
                    return
                }

                val soundListView = findViewById<ListView>(R.id.list_view)

                when(position){
                    0 -> {
                        when (paste) {
                            0 -> {
                                paste = 1
                                invalidateOptionsMenu()
                                Toast.makeText(applicationContext, R.string.change, Toast.LENGTH_LONG).show()
                            }
                            1 -> {
                                paste = 0
                                invalidateOptionsMenu()
                                Toast.makeText(applicationContext, R.string.change2, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                    1 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1 = 2
                        buttonA = 16
                        buttonB = 2
                        soundListView.adapter = nCustomAdapter
                        nCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    2 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1 = 2
                        buttonA = 16
                        buttonB = 2
                        soundListView.adapter = oCustomAdapter
                        oCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    3 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1 = 2
                        buttonA = 16
                        buttonB = 2
                        soundListView.adapter = pCustomAdapter
                        pCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    4 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1 = 2
                        buttonA = 16
                        buttonB = 2
                        soundListView.adapter = qCustomAdapter
                        qCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    5 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1 = 2
                        buttonA = 16
                        buttonB = 2
                        soundListView.adapter = rCustomAdapter
                        rCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    6 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1 = 2
                        buttonA = 16
                        buttonB = 1
                        selectEX()
                        soundListView.adapter = tCustomAdapter
                        tCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    7 -> {
                        textView.text = ""
                        textView2.text = ""
                        textView3.text = ""
                        textView4.text = ""
                        textView5.text = ""
                        textView6.text = ""
                        textView7.text = ""
                        textView8.text = ""
                        textView9.text = ""
                        textView10.text = ""
                        textView11.text = ""
                        textView12.text = ""
                        textView13.text = ""
                        textView14.text = ""
                        textView15.text = ""
                        sound1 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound2 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound3 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound4 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound5 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound6 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound7 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound8 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound9 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound10 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound11 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound12 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound13 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound14 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                        sound15 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        chSpinner.isFocusable = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val menuLamp = menu!!.findItem(R.id.menu1)
        if (menuSwitch) {
            menuLamp.setIcon(R.drawable.ic_baseline_play_arrow_24)
        } else {
            menuLamp.setIcon(R.drawable.ic_baseline_stop_24)
        }

        val menuLamp2 = menu.findItem(R.id.menu10)
        if (paste == 1) {
            menuLamp2.setIcon(R.drawable.ic_baseline_library_music_24_c)
        } else if (paste == 2) {
            menuLamp2.setIcon(R.drawable.ic_baseline_library_music_24)
        }

        return true
    }

    private var menuSwitch = true
    private var menuSwitch2 = true
    private var mediaRecorder = MediaRecorder()
    private var switch1 = 0


    @SuppressLint("SimpleDateFormat")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val soundListView = findViewById<ListView>(R.id.list_view)

        when (item.itemId) {

            R.id.menu1 -> {
                if (soundListView.isVisible) {
                    soundListView.visibility = View.INVISIBLE
                }
                if (switch1 == 1) {
                    lmp.stop()
                    soundPool.autoPause()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                } else {
                    lmp.start()
                    menuSwitch = false
                    invalidateOptionsMenu()
                    switch1 = 1
                }
                return true
            }

            R.id.menu5 -> {

                return true
            }

            R.id.menu6 -> {
                AlertDialog.Builder(this)
                        .setTitle(R.string.menu6)
                        .setPositiveButton("YES") { _, _ ->
                            finish()
                        }
                        .setNegativeButton("NO") { _, _ ->

                        }
                        .show()

                return true
            }

            R.id.menuPlus -> {
                lmp.volumePlus()
                return true
            }

            R.id.menuMinus -> {
                lmp.volumeMinus()
                return true
            }

            R.id.menu10 -> {
                if (soundListView.isVisible) {
                    soundListView.visibility = View.INVISIBLE
                }
                selectCh()
                return true
            }

            R.id.action_settings -> {
                if (soundListView.isVisible) {
                    soundListView.visibility = View.INVISIBLE
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        lmp.reset()
        lmp.release()
        mp.reset()
        mp.release()
        soundPool.autoPause()
        soundPool.release()

        mediaRecorder.reset()
        mediaRecorder.release()
        super.onDestroy()
    }

    override fun onPause() {
        menuSwitch = true
        invalidateOptionsMenu()
        switch1 = 2
        if (mp.isPlaying) {
            mp.stop()
            mp.prepare()
        }
        if (!menuSwitch2) {
            menuSwitch2 = true
            invalidateOptionsMenu()
            mediaRecorder.stop()
            Toast.makeText(applicationContext, R.string.button_setOnClickListener3, Toast.LENGTH_LONG).show()
        }

            lmp.stop()
            soundPool.autoPause()

        super.onPause()
    }
}
