package ru.tomsknipineft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tomsknipineft.entities.areaObjects.BackfillSite;
import ru.tomsknipineft.entities.linearObjects.Ktplp;
import ru.tomsknipineft.entities.oilPad.BackfillWell;
import ru.tomsknipineft.repositories.BackfillSiteRepository;
import ru.tomsknipineft.utils.exceptions.NoSuchEntityException;

@Service
@RequiredArgsConstructor
public class BackfillSiteService implements EntityProjectService {

    private final BackfillSiteRepository backfillSiteRepository;

    private BackfillSite findBackfillSiteFromRequest;

    /**
     * Поиск в БД количества ресурса необходимого для выполнения полевых ИИ
     *
     * @param backfillSite Инженерная подготовка площадки
     * @return количество необходимого ресурса
     */
    public Integer getResourceForEngSurveyBackfillSite(BackfillSite backfillSite) {
        if (backfillSite.isActive()) {
            this.findBackfillSiteFromRequest = backfillSiteRepository.findFirstBySquareGreaterThanEqual(backfillSite.getSquare()).orElseThrow(() ->
                    new NoSuchEntityException("Введено некорректное значение площади " + backfillSite.getSquare()));
            return findBackfillSiteFromRequest.getResourceForEngSurvey();
        }
        return 0;
    }

    /**
     * Поиск в БД количества ресурса необходимого для выполнения ЛИ
     *
     * @param backfillSite Инженерная подготовка площадки
     * @return количество необходимого ресурса
     */
    public Integer getResourceForLabResearchBackfillSite(BackfillSite backfillSite) {
        if (backfillSite.isActive()) {
            if (findBackfillSiteFromRequest == null) {
                this.findBackfillSiteFromRequest = backfillSiteRepository.findFirstBySquareGreaterThanEqual(backfillSite.getSquare()).orElseThrow(() ->
                        new NoSuchEntityException("Введено некорректное значение площади " + backfillSite.getSquare()));
            }
            return findBackfillSiteFromRequest.getResourceForLabResearch();
        }
        return 0;
    }

    /**
     * Поиск в БД количества ресурса необходимого для выполнения отчета ИИ
     *
     * @param backfillSite Инженерная подготовка площадки
     * @return количество необходимого ресурса
     */
    public Integer getResourceForEngSurveyReportBackfillSite(BackfillSite backfillSite) {
        if (backfillSite.isActive()) {
            if (findBackfillSiteFromRequest == null) {
                this.findBackfillSiteFromRequest = backfillSiteRepository.findFirstBySquareGreaterThanEqual(backfillSite.getSquare()).orElseThrow(() ->
                        new NoSuchEntityException("Введено некорректное значение площади " + backfillSite.getSquare()));
            }
            return findBackfillSiteFromRequest.getResourceForEngSurveyReport();
        }
        return 0;
    }

    /**
     * Поиск в БД количества ресурса необходимого для разработки РД
     *
     * @param backfillSite Инженерная подготовка площадки
     * @return количество необходимого ресурса
     */
    public Integer getResourceForWorkDocBackfillSite(BackfillSite backfillSite) {
        if (backfillSite.isActive()) {
            if (findBackfillSiteFromRequest == null) {
                this.findBackfillSiteFromRequest = backfillSiteRepository.findFirstBySquareGreaterThanEqual(backfillSite.getSquare()).orElseThrow(() ->
                        new NoSuchEntityException("Введено некорректное значение площади " + backfillSite.getSquare()));
            }
            return findBackfillSiteFromRequest.getResourceForWorkDoc();
        }
        return 0;
    }

    /**
     * Поиск в БД количества ресурса необходимого для разработки ПД
     *
     * @param backfillSite Инженерная подготовка площадки
     * @return количество необходимого ресурса
     */
    public Integer getResourceForProjDocBackfillSite(BackfillSite backfillSite) {
        if (backfillSite.isActive()) {
            return findBackfillSiteFromRequest.getResourceForProjDoc();
        }
        return 0;
    }

    /**
     * Поиск в БД количества ресурса необходимого для разработки СД
     *
     * @param backfillSite Инженерная подготовка площадки
     * @return количество необходимого ресурса
     */
    public Integer getResourceForEstDocBackfillSite(BackfillSite backfillSite) {
        if (backfillSite.isActive()) {
            return findBackfillSiteFromRequest.getResourceForEstDoc();
        }
        return 0;
    }

    /**
     * Получение сущности (Инженерная подготовка площадки) из БД
     *
     * @return сущность (Инженерная подготовка площадки)
     */
    public BackfillSite getFirst() {
        return backfillSiteRepository.findById(1L).orElseThrow(() ->
                new NoSuchEntityException("Площадка в базе данных отсутствует"));
    }
}
