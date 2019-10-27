package kuu.nagoya.toyoden.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kuu.nagoya.toyoden.R
import kuu.nagoya.toyoden.databinding.MainFragmentBinding
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableTimeItemViewEntity
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableViewEntity

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
                        else -> {
                        }
                    }
                }

            })
        }

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


        val timeTableAdapter = TimeTableAdapter(requireContext())
        timeTableAdapter.onPullToReducing = object : OnPullToReducing {
            override fun onPull() {
                val bottomSheetBehavior =
                    BottomSheetBehavior.from(binding.mainFragmentTimeTableBottomSheet)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }


        timeTableAdapter.submitList(
            listOf(
                TimeTableViewEntity(
                    0,
                    "Hello",
                    listOf(
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ), TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ), TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ), TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ), TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        )
                    )
                ),
                TimeTableViewEntity(
                    1,
                    "World",
                    listOf(
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ),
                        TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        ), TimeTableTimeItemViewEntity(
                            0,
                            "08:04",
                            "8分前"
                        )
                    )
                )
            )
        )

        binding.maiFragmentTimeTableViewPager.adapter = timeTableAdapter


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }
}
