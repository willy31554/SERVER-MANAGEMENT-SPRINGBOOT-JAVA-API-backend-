package io.getarrays.server.implementation;

import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;
import io.getarrays.server.service.ServerService;
import jakarta.servlet.Servlet;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static io.getarrays.server.enumeration.Status.SERVER_DOWN;
import static io.getarrays.server.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
	private final ServerRepo serverRepo;


	@Override
	public Server create(Server server) {
		log.info("Saving new Server : {}",server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepo.save(server);
	}



	@Override
	public Server ping(String ipAddress) throws IOException {
		log.info("Pinging server Ip Address{}",ipAddress);
		Server server=serverRepo.findByIpAddress(ipAddress);
		InetAddress address=InetAddress.getByName(ipAddress);
		server.setStatus(address.isReachable(10000)? SERVER_UP:SERVER_DOWN);
		serverRepo.save(server);
		return server;
	}

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching All servers");
		return serverRepo.findAll(PageRequest.of(0,limit)).toList();
	}

	@Override
	public Server get(long id) {
		log.info("Fetching servers by Id{}",id);
		return serverRepo.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Updating Server{}",server.getName());
		return serverRepo.save(server);
	}

	@Override
	public Boolean delete(long id) {
		log.info("Deleting Server{}",id);
		serverRepo.deleteById(id);
		return TRUE;
	}
	private String setServerImageUrl() {
		String [] imageNames={"server1.png","server2.png","server3.png","server4.png"};
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/"+imageNames[new Random().nextInt(4)]).toUriString();
	}
}
