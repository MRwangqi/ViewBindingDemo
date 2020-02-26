package com.kiwi.viewbinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kiwi.viewbinding.databinding.FragmentMainBinding
import com.kiwi.viewbinding.databinding.ItemPayBinding

/**
 *
 * 这是因为该 fragment 的生命周期与 activity 的生命周期不同，并且该fragment 可以超出其视图的生命周期，因此如果不将其设置为null，则可能会发生内存泄漏。
 * 另一个变量通过 !! 使一个变量为可空值而使另一个变量为非空值避免了空检查
 *
 */
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.text.text = "fragment bind"

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = NameAdapter(listOf("a","b","c"))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


class NameAdapter(private val nameList: List<String>) :
    RecyclerView.Adapter<NameAdapter.PaymentHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
        val itemBinding = ItemPayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val name: String = nameList[position]
        holder.bind(name)
    }

    override fun getItemCount(): Int = nameList.size

    class PaymentHolder(private val itemBinding: ItemPayBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(name: String) {
            itemBinding.text.text = name
        }
    }
}
