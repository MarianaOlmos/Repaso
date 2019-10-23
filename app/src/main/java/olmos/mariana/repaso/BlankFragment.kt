package olmos.mariana.repaso


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var etUrl:EditText? = null
    var btnIr:Button? = null
    var navegador:WebView? = null
    var progreso:ProgressBar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val miVista = inflater.inflate(R.layout.fragment_blank,container,false)
        //instanciar de widgets
        etUrl=miVista.findViewById(R.id.letrasfra)
        btnIr=miVista.findViewById(R.id.buttonbus)
        progreso=miVista.findViewById(R.id.barra)
        navegador=miVista.findViewById(R.id.web)

        val chromeClient=object:WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progreso!!.progress=newProgress
            }
        }

        navegador!!.webChromeClient = chromeClient

        navegador!!.settings.javaScriptEnabled=true

        btnIr!!.setOnClickListener {
            val url= etUrl!!.text.toString().trim()
            var urlFinal=""
            if(!url.startsWith("www")) {
                urlFinal = "http://$url"
            }else{

                urlFinal="https://www.google.com/search?q=$url"
            }
            navegador!!.loadUrl(urlFinal)
        }

        return miVista
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
