package com.jsb.android_jetpack_practice.view.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jsb.android_jetpack_practice.R
import com.jsb.android_jetpack_practice.databinding.FragmentBeerListBinding
import com.jsb.android_jetpack_practice.utils.SimpleLog
import com.jsb.android_jetpack_practice.view.adapters.BeerListAdapter
import com.jsb.android_jetpack_practice.viewmodel.BeerListViewModel

class BeerListFragment : Fragment()
{
    private var mBinding: FragmentBeerListBinding? = null
    private var mProgressDialog: Dialog? = null
    private lateinit var mBeerListViewModel: BeerListViewModel
    private lateinit var mBeerListAdapter: BeerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentBeerListBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBeerListViewModel = ViewModelProvider(this).get(BeerListViewModel::class.java)
        mBeerListViewModel.getBeerListFromAPI()

//        mBinding!!.rvBeerList.layoutManager = LinearLayoutManager(requireActivity())
        mBinding!!.rvBeerList.layoutManager = GridLayoutManager(requireActivity(), 2)
        mBeerListAdapter = BeerListAdapter(this@BeerListFragment)
        mBinding!!.rvBeerList.adapter = mBeerListAdapter

        beerListViewModelObserver()
    }

    private fun beerListViewModelObserver() {
        //Response
        mBeerListViewModel.beerListResponse.observe(viewLifecycleOwner,
            { beerListResponse ->
                beerListResponse?.let {
//                    SimpleLog.i("Beer List Response ${beerListResponse}")
                    if(it.isNotEmpty()){
                        mBinding!!.rvBeerList.visibility = View.VISIBLE
                        mBinding!!.tvNoBeerAddedYet.visibility = View.GONE

                        mBeerListAdapter.beerList(it)
                    }else{
                        mBinding!!.rvBeerList.visibility = View.GONE
                        mBinding!!.tvNoBeerAddedYet.visibility = View.VISIBLE
                    }
                }
            }
        )

        //Error
        mBeerListViewModel.beerListLoadingError.observe(viewLifecycleOwner,
            { dataError ->
                dataError?.let {
                    SimpleLog.e("Beer List API ERROR $dataError")

                }
            }
        )

        //Load
        mBeerListViewModel.loadBeerList.observe(viewLifecycleOwner,
            { loadRandomBeer ->
                loadRandomBeer?.let {
                    SimpleLog.i("Beer List Loading $loadRandomBeer")

                }
            }
        )
    }

    private fun showCustomProgressDialog(){
        mProgressDialog = Dialog(requireActivity())
        mProgressDialog?.run {
            setContentView(R.layout.dialog_custom_progress)
            show()
        }
    }

    private fun hideProgressDialog(){
        mProgressDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}