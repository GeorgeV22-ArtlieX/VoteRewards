package com.GeorgeV22.VoteRewards.Hooks;

import org.bukkit.entity.Player;

import com.GeorgeV22.VoteRewards.Main;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PAPI extends PlaceholderExpansion {

	private Main plugin;

	public PAPI(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getIdentifier() {
		return "voterewards";
	}

	@Override
	public String getPlugin() {
		return "VoteRewards";
	}

	@Override
	public String getAuthor() {
		return "ArtlieX";
	}

	@Override
	public String getVersion() {
		return plugin.getDescription().getVersion();
	}

	@Override
	public boolean persist() {
		return true;
	}

	@Override
	public boolean canRegister() {
		return true;
	}

	@Override
	public String onPlaceholderRequest(Player player, String identifier) {
		if (player == null) {
			return "null";
		}

		if (identifier.equalsIgnoreCase("player_player_votes")) {
			return String.valueOf(plugin.getVotes().getVotes(player));
		}
		if (identifier.equalsIgnoreCase("total_votes")) {
			return String.valueOf(plugin.getData().getInt("VoteParty_Votes"));
		}
		if (identifier.equalsIgnoreCase("votes_until")) {
			return String
					.valueOf(plugin.getConfig().getInt("VoteParty.Votes") - plugin.getData().getInt("VoteParty_Votes"));
		}
		if (identifier.equalsIgnoreCase("votes_needed")) {
			return String.valueOf(plugin.getConfig().getInt("VoteParty.Votes"));
		}
		if (identifier.equalsIgnoreCase("top_voter")) {
			return String.valueOf(plugin.getVotes().getTopVotePlayer(1));
		}
		return null;
	}
}
