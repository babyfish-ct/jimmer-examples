import { Button, CircularProgress, Popover, Stack } from "@mui/material";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { FC, memo, useCallback, useState } from "react";
import { api } from "../common/ApiInstance";
import { MessageDialog, MessageInfo } from "../common/MessageDialog";
import { BookRow } from "./BookTypes";

export const BookDeleter: FC<{
    readonly row?: BookRow,
    readonly anchorEl?: HTMLElement,
    readonly onClose: () => void
}> = memo(({row, anchorEl, onClose}) => {

    const queryClient = useQueryClient();

    const { isLoading, mutateAsync } = useMutation({
        mutationFn: () => api.bookService.deleteBook({id: row!!.id}),
        onSettled: () => queryClient.invalidateQueries(),
    });

    const [messageInfo, setMessageInfo] = useState<MessageInfo>();

    const onYesClick = useCallback(async () => {
        try {
            mutateAsync();
        } catch (ex) {
            setMessageInfo({
                severity: "error",
                title: "Error",
                content: "Failed to delete book"
            });
            return;
        }
        setMessageInfo({
            title: "Success",
            content: "The book has been deleted"
        });
    }, [mutateAsync]);

    const onNoClick = useCallback(() => {
        onClose();
    }, [onClose]);

    const onMessageClose = useCallback(() => {
        setMessageInfo(undefined);
        onClose();
    }, [onClose]);

    return (
        <>
            <Popover 
            open={row !== undefined && anchorEl !== undefined}
            anchorOrigin={{
                horizontal: 'right',
                vertical: 'bottom'
            }}
            onClose={onNoClick}
            anchorEl={anchorEl}>
                <div style={{padding: '1rem', width: 300}}>
                    <Stack spacing={1}>
                        <div>Are you sure to delete book "{row?.name}"(edition {row?.edition})?</div>
                        <Stack direction="row" spacing={1} alignContent="center" style={{width: '100%'}}>
                            <Button variant="outlined" onClick={onYesClick}>
                                {isLoading ? <CircularProgress/> : "Yes"}
                            </Button>
                            <Button variant="outlined" onClick={onNoClick}>No</Button>
                        </Stack>
                    </Stack>
                </div>
            </Popover>
            <MessageDialog info={messageInfo} onClose={onMessageClose}/>
        </>
    );
});