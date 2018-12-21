package api.resources;

import beans.crud.AlbumBean;
import com.kumuluz.ee.logs.cdi.Log;
import entities.Album;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("albums")
@ApplicationScoped
@Tags(value = @Tag(name = "albums"))
@Log
public class AlbumResource {

    @Inject
    AlbumBean albumBean;

    @Operation(
            summary = "Get albums",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of albums"),
            }
    )
    @GET
    public Response getAlbums() {
        return Response.ok(albumBean.getAlbums()).build();
    }

    @Operation(
            summary = "Get album",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Album with given ID"),
                    @ApiResponse(responseCode = "404", description = "Album with ID not found"),
            },
            parameters = {
                    @Parameter(name = "albumId", in = ParameterIn.PATH),
            }
    )
    @GET
    @Path("{albumId}")
    public Response getAlbumById(@PathParam("albumId") int albumId) {
        Album a = albumBean.getAlbum(albumId);
        if (a == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(a).build();
    }

    @Operation(
            summary = "Get albums by artist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Albums for given artist"),
            },
            parameters = {
                    @Parameter(name = "artistId", in = ParameterIn.PATH),
            }
    )
    @GET
    @Path("artist/{artistId}")
    public Response getAlbumsByArtist(@PathParam("artistId") int artistId) {
        return Response.ok(albumBean.getAlbumsByArtist(artistId)).build();
    }

    @Operation(
            summary = "Get albums by user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Albums by given user"),
            },
            parameters = {
                    @Parameter(name = "userId", in = ParameterIn.PATH),
            }
    )
    @GET
    @Path("user/{userId}")
    public Response getAlbumsByUser(@PathParam("userId") int userId) {
        return Response.ok(albumBean.getAlbumsByUser(userId)).build();
    }

    //

    @Operation(
            summary = "Create album",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Album created"),
                    @ApiResponse(responseCode = "409", description = "Conflict when trying to create album"),
            },
            requestBody = @RequestBody(content = {@Content(schema = @Schema(implementation = Album.class))})
    )
    @POST
    public Response createAlbum(Album album) {
        album = albumBean.insertAlbum(album);
        if (album == null) {
            return Response.status(Response.Status.CONFLICT).entity(album).build();
        } else {
            return Response.status(Response.Status.CREATED).entity(album).build();
        }
    }

    @Operation(
            summary = "Update album",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Album updated"),
                    @ApiResponse(responseCode = "404", description = "Album with ID does not exist")
            }
    )
    @PUT
    @Path("{albumId}")
    public Response updateAlbum(@PathParam("albumId") int albumId, Album album) {

        album = albumBean.updateAlbum(albumId, album);

        if (album == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(album).build();
        }
    }

    @Operation(
            summary = "Delete album",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Album deleted"),
                    @ApiResponse(responseCode = "404", description = "Album with ID does not exist")
            }
    )
    @DELETE
    @Path("{albumId}")
    public Response deleteAlbum(@PathParam("albumId") int albumId) {

        boolean deleted = albumBean.deleteAlbum(albumId);

        if (deleted) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
