package com.uscosoft.angular4springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.uscosoft.angular4springboot.model.Album;
import com.uscosoft.angular4springboot.service.mapper.AlbumMapper;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	private final String LIST_SQL = "select album_id, id, title, url, thumbnailurl from album";

	@Override
	public List<Album> ListAllPhotos() {
		return jdbcTemplate.query(LIST_SQL, new AlbumMapper());
	}

}
