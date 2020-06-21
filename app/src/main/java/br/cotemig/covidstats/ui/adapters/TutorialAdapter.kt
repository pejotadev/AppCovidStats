package br.cotemig.covidstats.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.cotemig.covidstats.R
import br.cotemig.covidstats.models.TutorialValues
import com.bumptech.glide.Glide

class TutorialAdapter(var context: Context, var gallery: List<TutorialValues>) :
    RecyclerView.Adapter<TutorialAdapter.TutorialHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tuto, parent, false)
        return TutorialHolder(view)
    }

    override fun getItemCount(): Int {
        return gallery.size
    }

    fun getItemIndex(): Int {
        return gallery.size
    }

    override fun onBindViewHolder(holder: TutorialHolder, position: Int) {
        holder.bind(context, gallery[position])
    }

    class TutorialHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, i: TutorialValues) {

            var image = itemView.findViewById<ImageView>(R.id.image)
            var texto = itemView.findViewById<TextView>(R.id.texto)
            Glide.with(context).load(i.imagem).into(image)
            texto.text = i.frase.toString()

        }

    }

}


