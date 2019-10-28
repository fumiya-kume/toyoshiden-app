package kuu.nagoya.toyoden.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kuu.nagoya.toyoden.R
import kuu.nagoya.toyoden.databinding.MainFragmentBinding
import kuu.nagoya.toyoden.ui.main.view.OnPullToReducing
import kuu.nagoya.toyoden.ui.main.view.TimeTableAdapter
import kuu.nagoya.toyoden.ui.main.view.TimeTableViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var timeTableViewModel: TimeTableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        timeTableViewModel = ViewModelProviders.of(this).get(TimeTableViewModel::class.java)

        val binding = MainFragmentBinding.inflate(
            layoutInflater,
            container,
            false
        )


        binding.mainFragmentShowTrainFloatingActionButton.setOnClickListener {
            BottomSheetBehavior.from(binding.mainFragmentTimeTableBottomSheet).run {
                this.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            binding.mainFragmentShowTrainFloatingActionButton.hide()
        }

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

        binding.maiFragmentTimeTableViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                timeTableViewModel.timeTableLiveData.value?.elementAt(position)?.let {
                    val latlon = LatLng(it.lat, it.lon)

                    val maker = MarkerOptions().apply {
                        this.position(latlon)
                        this.title(it.stationName)
                    }

                    val cameraLatlon = latlon.latitude + 0.001
                    val camearaLocation = LatLng(cameraLatlon, latlon.longitude)


                    val camera = CameraPosition
                        .builder()
                        .zoom(15.0F)
                        .target(camearaLocation)
                        .build()

                    val supportMapFragment =
                        childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                    supportMapFragment.getMapAsync {
                        it.animateCamera(
                            CameraUpdateFactory.newCameraPosition(camera),
                            object : GoogleMap.CancelableCallback {
                                override fun onFinish() {

                                }

                                override fun onCancel() {

                                }
                            }
                        )
                        it.addMarker(maker)
                    }
                }
            }
        })
        
        val timeTableAdapter = TimeTableAdapter(requireContext())
        timeTableAdapter.onPullToReducing =
            object :
                OnPullToReducing {
                override fun onPull() {
                    val bottomSheetBehavior =
                        BottomSheetBehavior.from(binding.mainFragmentTimeTableBottomSheet)
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }


        timeTableViewModel
            .timeTableLiveData
            .observeForever {
                timeTableAdapter.submitList(it)
            }

        binding.maiFragmentTimeTableViewPager.adapter = timeTableAdapter

        BottomSheetBehavior.from(binding.mainFragmentTimeTableBottomSheet).run {
            this.state = BottomSheetBehavior.STATE_HIDDEN
            this.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            binding.mainFragmentShowTrainFloatingActionButton.show()
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            timeTableAdapter.submitList(
                                timeTableAdapter.currentList.map { it.copy(isVisibleAllTimeTable = false) }
                            )
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            timeTableAdapter.submitList(
                                timeTableAdapter.currentList.map { it.copy(isVisibleAllTimeTable = true) }
                            )
                        }
                        else -> {
                        }
                    }
                }

            })
        }

        return binding.root
    }
}