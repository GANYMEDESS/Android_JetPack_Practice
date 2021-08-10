package com.jsb.android_jetpack_practice.view.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jsb.android_jetpack_practice.R
import com.jsb.android_jetpack_practice.databinding.FragmentRandomBeerBinding
import com.jsb.android_jetpack_practice.model.entities.RandomBeer
import com.jsb.android_jetpack_practice.utils.SimpleLog
import com.jsb.android_jetpack_practice.viewmodel.RandomBeerViewModel

class RandomBeerFragment: Fragment()
{
    private var mBinding: FragmentRandomBeerBinding? = null
    private var mProgressDialog: Dialog? = null
    private lateinit var mRandomBeerViewModel: RandomBeerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentRandomBeerBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRandomBeerViewModel = ViewModelProvider(this).get(RandomBeerViewModel::class.java)
        mRandomBeerViewModel.getRandomBeerFromAPI()

        randomBeerViewModelObserver()

        mBinding!!.srlRandomBeer.setOnRefreshListener {
            mRandomBeerViewModel.getRandomBeerFromAPI()
        }
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

    private fun randomBeerViewModelObserver(){
        //Response
        mRandomBeerViewModel.randomBeerResponse.observe(viewLifecycleOwner,
            { randomBeerResponse ->
                randomBeerResponse?.let {
                    SimpleLog.i("Random Beer Response ${randomBeerResponse}")

                    if(mBinding!!.srlRandomBeer.isRefreshing){
                        mBinding!!.srlRandomBeer.isRefreshing = false
                    }

                    setRandomBeerResponseInUI(randomBeerResponse.get(0))
                }
            }
        )

        //Error
        mRandomBeerViewModel.randomBeerLoadingError.observe(viewLifecycleOwner,
            { dataError ->
                dataError?.let {
                    SimpleLog.e("Random Beer API ERROR $dataError")

                    if(mBinding!!.srlRandomBeer.isRefreshing){
                        mBinding!!.srlRandomBeer.isRefreshing = false
                    }
                }
            }
        )

        //Load
        mRandomBeerViewModel.loadRandomBeer.observe(viewLifecycleOwner,
            { loadRandomBeer ->
                loadRandomBeer?.let {
                    SimpleLog.i("Random Beer Loading $loadRandomBeer")

                    if(loadRandomBeer && !mBinding!!.srlRandomBeer.isRefreshing){
                        showCustomProgressDialog()
                    }else{
                        hideProgressDialog()
                    }
                }
            }
        )
    }

    private fun setRandomBeerResponseInUI(beer: RandomBeer.BeersItem) {
        mBinding?.run {
            // Image
            Glide.with(requireActivity())
                .load(beer.image_url)
                .into(ivBeerImage)

            // Text
            tvTitle.text = beer.name
            tvCategory.text = beer.tagline
            tvAbvAmount.text = beer.abv.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}