package com.ItemBox.Main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.ItemBox.AS.AS;
import com.ItemBox.GUI.ExampleGui;


public class Main extends JavaPlugin

{
	

	@Override
	public void onEnable() 	
	
	{

		PluginDescriptionFile pdfFile = this.getDescription();
		Bukkit.getConsoleSender().sendMessage(pdfFile.getName() + "버전" + pdfFile.getVersion() + "실행완료");
		
		//getServer().getPluginManager().registerEvents(new ExampleGui(), this);
		getServer().getPluginManager().registerEvents(new AS(this), this);
		
	}

	@Override
	public void onDisable() 
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		Bukkit.getConsoleSender().sendMessage(pdfFile.getName() + "플러그인 오프!");
		
	}

}
