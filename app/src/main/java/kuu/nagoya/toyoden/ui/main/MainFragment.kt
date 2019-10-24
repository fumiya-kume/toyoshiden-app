package kuu.nagoya.toyoden.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.main_fragment.*
import kuu.nagoya.toyoden.R
import kuu.nagoya.toyoden.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(
            layoutInflater,
            container,
            false
        )

        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync {
            it.isMyLocationEnabled = true
            val toyohashiStationLocation = LatLng(34.7629443, 137.3820671)
            val mizuhoLocation = LatLng(34.7641776, 137.38351979999993)

            val camera = CameraPosition
                .builder()
                .zoom(13.0F)
                .target(toyohashiStationLocation)
                .build()


            it.moveCamera(CameraUpdateFactory.newCameraPosition(camera))

            val toyohashiStationMaker = MarkerOptions()
            toyohashiStationMaker.position(toyohashiStationLocation)
            toyohashiStationMaker.title("豊橋駅")
            toyohashiStationMaker.snippet("snipet")
            toyohashiStationMaker.icon(BitmapDescriptorFactory.fromResource(R.drawable.train))


            it.addMarker(toyohashiStationMaker)
        }

//        val activity = requireActivity() as AppCompatActivity
//        activity.setSupportActionBar(binding.mainFragmentBottomAppBar)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }

//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        inflater?.inflate(R.menu.main, menu)
//    }






}
